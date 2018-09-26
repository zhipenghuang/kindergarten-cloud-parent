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
public class KeyConfiguration {

    @Value("${jwt.rsa-secret}")
    private String userSecret;

    @Value("${client.rsa-secret}")
    private String serviceSecret;

    private byte[] userPubKey;

    private byte[] userPriKey;

    private byte[] servicePubKey;

    private byte[] servicePriKey;

}
