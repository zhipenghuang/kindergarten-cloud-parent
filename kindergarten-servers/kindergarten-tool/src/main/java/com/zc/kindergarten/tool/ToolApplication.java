package com.zc.kindergarten.tool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hzp
 * @create 2018/10/12.
 */

@SpringBootApplication
@EnableEurekaClient
public class ToolApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ToolApplication.class).web(true).run(args);
	}
}
