package com.zc.kindergarten.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HystrixApplication.class, args);
    }
}
