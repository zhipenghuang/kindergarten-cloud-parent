package com.zc.kindergarten.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.zc.kindergarten.auth.client.config.ServiceAuthConfig;
import com.zc.kindergarten.auth.client.config.ServiceAuthUtil;
import com.zc.kindergarten.auth.client.config.UserAuthConfig;
import com.zc.kindergarten.auth.client.config.UserAuthUtil;
import com.zc.kindergarten.common.context.BaseContextHandler;
import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import com.zc.kindergarten.common.vo.LogInfo;
import com.zc.kindergarten.common.vo.PermissionInfo;
import com.zc.kindergarten.gateway.feign.ILogService;
import com.zc.kindergarten.gateway.feign.IUserService;
import com.zc.kindergarten.gateway.utils.DbLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hzp
 * @create 2018/10/12.
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {
    @Autowired
    @Lazy
    private IUserService userService;
    @Autowired
    @Lazy
    private ILogService logService;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    private static final String GATE_WAY_PREFIX = "/api";
    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private ServiceAuthUtil serviceAuthUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        log.info("check token and user permission....");
        LinkedHashSet requiredAttribute = serverWebExchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = serverWebExchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()) {
                URI next = iterator.next();
                if (next.getPath().startsWith(GATE_WAY_PREFIX)) {
                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
                }
            }
        }
        final String method = request.getMethod().toString();
        BaseContextHandler.setToken(null);
        ServerHttpRequest.Builder mutate = request.mutate();
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
        }
        IjwtInfo user = null;
        try {
            user = getJWTUser(request, mutate);
        } catch (Exception e) {
            log.error("用户Token过期异常", e);
            return getVoidMono(serverWebExchange, new ResponseEntity(AuthErrors.USER_TOKEN_EXPIRED));
        }
        ResponseEntity<List<PermissionInfo>> allPermissionInfo = userService.getAllPermissionInfo();
        List<PermissionInfo> permissionIfs = allPermissionInfo.getData();
        // 判断资源是否启用权限约束
        Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
        List<PermissionInfo> result = stream.collect(Collectors.toList());
        PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
        if (permissions.length > 0) {
            if (checkUserPermission(permissions, serverWebExchange, user)) {
                return getVoidMono(serverWebExchange, new ResponseEntity(AuthErrors.USER_FORBIDDEN_DOES_NOT_HAS_PERMMISION));
            }
        }
        // 申请客户端密钥头
        mutate.header(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken());
        ServerHttpRequest build = mutate.build();
        return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());

    }

    /**
     * 网关抛异常
     *
     * @param serverWebExchange,ResponseEntity
     */
    @NotNull
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, ResponseEntity body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }


    /**
     * 获取目标权限资源
     *
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return
     */
    private Stream<PermissionInfo> getPermissionIfs(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
        return serviceInfo.parallelStream().filter(new Predicate<PermissionInfo>() {
            @Override
            public boolean test(PermissionInfo permissionInfo) {
                String uri = permissionInfo.getUri();
                if (uri.indexOf("{") > 0) {
                    uri = uri.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                }
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find())
                        && method.equals(permissionInfo.getMethod());
            }
        });
    }

    private void setCurrentUserInfoAndLog(ServerWebExchange serverWebExchange, IjwtInfo user, PermissionInfo pm) {
        String host = serverWebExchange.getRequest().getRemoteAddress().toString();
        LogInfo logInfo = new LogInfo(pm.getMenu(), pm.getName(), pm.getUri(), new Date(), user.getId(), user.getName(), host);
        DbLog.getInstance().setLogService(logService).offerQueue(logInfo);
    }

    /**
     * 返回token中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IjwtInfo getJWTUser(ServerHttpRequest request, ServerHttpRequest.Builder ctx) throws Exception {
        List<String> strings = request.getHeaders().get(userAuthConfig.getTokenHeader());
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }
        if (StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        ctx.header(userAuthConfig.getTokenHeader(), authToken);
        BaseContextHandler.setToken(authToken);
        return userAuthUtil.getInfoFromToken(authToken);
    }


    private boolean checkUserPermission(PermissionInfo[] permissions, ServerWebExchange ctx, IjwtInfo user) {
        ResponseEntity<List<PermissionInfo>> permissionByUsername = userService.getPermissionByUsername(user.getUniqueName());
        List<PermissionInfo> permissionInfos = permissionByUsername.getData();
        PermissionInfo current = null;
        for (PermissionInfo info : permissions) {
            boolean anyMatch = permissionInfos.parallelStream().anyMatch(new Predicate<PermissionInfo>() {
                @Override
                public boolean test(PermissionInfo permissionInfo) {
                    return permissionInfo.getCode().equals(info.getCode());
                }
            });
            if (anyMatch) {
                current = info;
                break;
            }
        }
        if (current == null) {
            return true;
        } else {
            if (!RequestMethod.GET.toString().equals(current.getMethod())) {
                setCurrentUserInfoAndLog(ctx, user, current);
            }
            return false;
        }
    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private Mono<Void> setFailedRequest(ServerWebExchange serverWebExchange, String body, int code) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        return serverWebExchange.getResponse().setComplete();
    }

}
