package com.zc.kindergarten.auth.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
@Data
public class UserConfiguration {
    @Value("${jwt.token-header}")
    private String userTokenHeader;
}
