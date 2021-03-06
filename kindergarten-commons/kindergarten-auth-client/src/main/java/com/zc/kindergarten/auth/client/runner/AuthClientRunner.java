package com.zc.kindergarten.auth.client.runner;

import com.zc.kindergarten.auth.client.config.ServiceAuthConfig;
import com.zc.kindergarten.auth.client.config.UserAuthConfig;
import com.zc.kindergarten.auth.client.feign.ServiceAuthFeign;
import com.zc.kindergarten.common.msg.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 监听完成时触发
 *
 * @author hzp
 * @create 2018/9/21.
 */
@Configuration
@Slf4j
public class AuthClientRunner implements CommandLineRunner {

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化加载用户pubKey");
        try {
            refreshUserPubKey();
        } catch (Exception e) {
            log.error("初始化加载用户pubKey失败,1分钟后自动重试!", e);
        }
        log.info("初始化加载服务pubKey");
        try {
            refreshServicePubKey();
        } catch (Exception e) {
            log.error("初始化加载服务pubKey失败,1分钟后自动重试!", e);
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey() {
        ResponseEntity<byte[]> resp = serviceAuthFeign.getUserPublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getEcode() == 0) {
            this.userAuthConfig.setPubKeyByte(resp.getData());
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshServicePubKey() {
        ResponseEntity<byte[]> resp = serviceAuthFeign.getServicePublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getEcode() == 0) {
            this.serviceAuthConfig.setPubKeyByte(resp.getData());
        }
    }

}