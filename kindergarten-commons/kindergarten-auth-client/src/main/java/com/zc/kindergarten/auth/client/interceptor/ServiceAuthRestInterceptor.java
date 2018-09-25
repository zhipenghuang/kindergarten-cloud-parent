package com.zc.kindergarten.auth.client.interceptor;

import com.zc.kindergarten.auth.client.annotation.IgnoreClientToken;
import com.zc.kindergarten.auth.client.config.ServiceAuthConfig;
import com.zc.kindergarten.auth.client.config.ServiceAuthUtil;
import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.exception.auth.ClientForbiddenException;
import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);

	@Autowired
	private ServiceAuthUtil serviceAuthUtil;

	@Autowired
	private ServiceAuthConfig serviceAuthConfig;

	private List<String> allowedClient;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 配置该注解，说明不进行服务拦截
		IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
		if (annotation == null) {
			annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
		}
		if (annotation != null) {
			return super.preHandle(request, response, handler);
		}

		String token = request.getHeader(serviceAuthConfig.getTokenHeader());
		IjwtInfo infoFromToken = serviceAuthUtil.getInfoFromToken(token);
		String uniqueName = infoFromToken.getUniqueName();
		for (String client : serviceAuthUtil.getAllowedClient()) {
			if (client.equals(uniqueName)) {
				return super.preHandle(request, response, handler);
			}
		}
		throw new ClientForbiddenException(AuthErrors.CLIENT_FORBIDDEN);
	}
}
