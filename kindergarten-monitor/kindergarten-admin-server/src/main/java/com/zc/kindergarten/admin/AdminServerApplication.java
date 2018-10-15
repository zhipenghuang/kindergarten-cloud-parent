package com.zc.kindergarten.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2018-10-15 12:44
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
