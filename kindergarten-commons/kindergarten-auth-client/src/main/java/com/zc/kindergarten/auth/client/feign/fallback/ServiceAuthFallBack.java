package com.zc.kindergarten.auth.client.feign.fallback;

import com.zc.kindergarten.auth.client.feign.ServiceAuthFeign;
import com.zc.kindergarten.common.msg.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzp
 * @create 2018/10/10.
 */
@Service
@Slf4j
public class ServiceAuthFallBack implements ServiceAuthFeign {
    @Override
    public ResponseEntity<List<String>> getAllowedClient(String serviceId, String secret) {
        log.error("调用{}异常{}{}", "getAllowedClient", serviceId, secret);
        return null;
    }

    @Override
    public ResponseEntity<String> getAccessToken(String clientId, String secret) {
        log.error("调用{}异常{}{}", "getPermissionByUsername", clientId, secret);
        return null;
    }

    @Override
    public ResponseEntity<byte[]> getServicePublicKey(String clientId, String secret) {
        log.error("调用{}异常{}{}", "getPermissionByUsername", clientId, secret);
        return null;
    }

    @Override
    public ResponseEntity<byte[]> getUserPublicKey(String clientId, String secret) {
        log.error("调用{}异常{}{}", "getPermissionByUsername", clientId, secret);
        return null;
    }
}
