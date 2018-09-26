package com.zc.kindergarten.auth.server.interceptor;

import com.zc.kindergarten.auth.server.config.ClientConfiguration;
import com.zc.kindergarten.auth.server.config.ClientTokenUtil;
import com.zc.kindergarten.auth.server.service.AuthClientService;
import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.exception.auth.ClientForbiddenException;
import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private ClientConfiguration clientConfiguration;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String token = request.getHeader(clientConfiguration.getClientTokenHeader());
        IjwtInfo infoFromToken = clientTokenUtil.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        for(String client: authClientService.getAllowedClient(clientConfiguration.getClientId())){
            if(client.equals(uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException(AuthErrors.CLIENT_FORBIDDEN);
    }
}
