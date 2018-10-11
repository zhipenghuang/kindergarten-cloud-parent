package com.zc.kindergarten.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients({"com.zc.kindergarten.auth.server.feign"})
public class AuthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }
}
