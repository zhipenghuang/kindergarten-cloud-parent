package com.zc.kindergarten.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ${DESCRIPTION}
 * 启动一个服务注册中心提供给其他应用进行对话
 * @author hzp
 * @create 2018-09-19 12:44
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }

    /**
     * ${DESCRIPTION}
     *
     * @author hzp : 因为 Spring Security 默认开启了所有 CSRF ×××防御，需要禁用 /eureka 的防御,否则服务无法注册
     * @create 2018-09-19 12:44
     */
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().ignoringAntMatchers("/eureka/**");
            super.configure(http);
        }
    }
}
