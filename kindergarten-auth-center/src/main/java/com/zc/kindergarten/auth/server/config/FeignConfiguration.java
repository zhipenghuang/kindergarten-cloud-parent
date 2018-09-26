package com.zc.kindergarten.auth.server.config;

import com.zc.kindergarten.auth.server.interceptor.ClientTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
public class FeignConfiguration {

    @Bean
    ClientTokenInterceptor getClientTokenInterceptor(){
        return new ClientTokenInterceptor();
    }
}
