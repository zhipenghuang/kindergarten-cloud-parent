package com.zc.kindergarten.auth.server.interceptor;

import com.zc.kindergarten.auth.server.config.ClientConfiguration;
import com.zc.kindergarten.auth.server.service.AuthClientService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ClientTokenInterceptor implements RequestInterceptor {

    @Autowired
    private ClientConfiguration clientConfiguration;
    @Autowired
    private AuthClientService authClientService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            requestTemplate.header(clientConfiguration.getClientTokenHeader(), authClientService.apply(clientConfiguration.getClientId(), clientConfiguration.getClientSecret()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
