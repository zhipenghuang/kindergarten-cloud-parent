package com.zc.kindergarten.usercenter;

import com.zc.kindergarten.auth.client.annotation.EnableAceAuthClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients({"com.zc.kindergarten.auth.client.feign"})
@EnableScheduling
@EnableAceAuthClient
@EnableTransactionManagement
public class UsercenterApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(UsercenterApplication.class).web(true).run(args);    }
}
