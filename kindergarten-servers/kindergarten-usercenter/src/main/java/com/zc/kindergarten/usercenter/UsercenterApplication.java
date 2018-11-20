package com.zc.kindergarten.usercenter;

import com.zc.kindergarten.auth.client.annotation.EnableAceAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients({"com.zc.kindergarten.auth.client.feign"})
@EnableScheduling
@EnableAceAuthClient
@EnableTransactionManagement
@EnableSwagger2
public class UsercenterApplication {
	public static void main(String[] args) {
        SpringApplication.run(UsercenterApplication.class, args);  }
}
