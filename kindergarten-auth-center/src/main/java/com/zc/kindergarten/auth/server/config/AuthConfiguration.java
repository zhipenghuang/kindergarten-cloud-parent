package com.zc.kindergarten.auth.server.config;

import com.zc.kindergarten.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
public class AuthConfiguration {
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }
}
