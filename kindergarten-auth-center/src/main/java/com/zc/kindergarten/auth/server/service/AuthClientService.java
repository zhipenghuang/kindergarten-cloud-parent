package com.zc.kindergarten.auth.server.service;

import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public interface AuthClientService {

    String apply(String clientId, String secret) throws Exception;

    /**
     * 获取授权的客户端列表
     *
     * @param serviceId
     * @param secret
     * @return
     */
    List<String> getAllowedClient(String serviceId, String secret);

    /**
     * 获取服务授权的客户端列表
     *
     * @param serviceId
     * @return
     */
    List<String> getAllowedClient(String serviceId);

    void registryClient();

    void validate(String clientId, String secret) throws Exception;
}
