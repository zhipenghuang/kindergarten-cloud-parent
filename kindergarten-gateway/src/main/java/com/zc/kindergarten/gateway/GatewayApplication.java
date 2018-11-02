package com.zc.kindergarten.gateway;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.zc.kindergarten.auth.client.annotation.EnableAceAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableAceAuthClient
@EnableFeignClients({"com.zc.kindergarten.auth.client.feign","com.zc.kindergarten.gateway.feign"})
@EnableCircuitBreaker
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
