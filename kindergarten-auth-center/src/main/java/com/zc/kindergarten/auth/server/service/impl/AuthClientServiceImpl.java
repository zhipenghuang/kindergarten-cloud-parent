package com.zc.kindergarten.auth.server.service.impl;

import com.zc.kindergarten.auth.server.config.ClientTokenUtil;
import com.zc.kindergarten.auth.server.entity.AuthClient;
import com.zc.kindergarten.auth.server.mapper.AuthClientMapper;
import com.zc.kindergarten.auth.server.service.AuthClientService;
import com.zc.kindergarten.auth.server.vo.ClientInfo;
import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.exception.auth.ClientInvalidException;
import com.zc.kindergarten.common.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Service
public class AuthClientServiceImpl implements AuthClientService {
    @Autowired
    private AuthClientMapper authClientMapper;
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private DiscoveryClient discovery;
    private ApplicationContext context;

    @Autowired
    public AuthClientServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public String apply(String clientId, String secret) throws Exception {
        AuthClient client = getClient(clientId, secret);
        return clientTokenUtil.generateToken(new ClientInfo(client.getCode(), client.getName(), client.getId().toString()));
    }

    private AuthClient getClient(String clientId, String secret) {
        AuthClient client = new AuthClient();
	    client.setCreatedAt(null);
	    client.setUpdatedAt(null);
        client.setCode(clientId);
        client = authClientMapper.selectOne(client);
        if (client == null || !client.getSecret().equals(secret)) {
            throw new ClientInvalidException(AuthErrors.CLIENT_NOT_FOUND_OR_CLIENT_SECRET_IS_ERROR);
        }
        return client;
    }

    @Override
    public void validate(String clientId, String secret) throws Exception {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = authClientMapper.selectOne(client);
        if (client == null || !client.getSecret().equals(secret)) {
            throw new ClientInvalidException(AuthErrors.CLIENT_NOT_FOUND_OR_CLIENT_SECRET_IS_ERROR);
        }
    }

    @Override
    public List<String> getAllowedClient(String clientId, String secret) {
        AuthClient info = this.getClient(clientId, secret);
        List<String> clients = authClientMapper.selectAllowedClient(info.getId() + "");
        if (clients == null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    public List<String> getAllowedClient(String serviceId) {
        AuthClient info = getClient(serviceId);
        List<String> clients = authClientMapper.selectAllowedClient(info.getId() + "");
        if (clients == null) {
            new ArrayList<String>();
        }
        return clients;
    }

    private AuthClient getClient(String clientId) {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = authClientMapper.selectOne(client);
        return client;
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void registryClient() {
        // 自动注册节点
        discovery.getServices().forEach((name) -> {
            AuthClient client = new AuthClient();
            client.setName(name);
            client.setCode(name);
            AuthClient dbClient = authClientMapper.selectOne(client);
            if (dbClient == null) {
                client.setSecret(UUIDUtils.generateShortUuid());
                authClientMapper.insert(client);
            }
        });
    }
}
