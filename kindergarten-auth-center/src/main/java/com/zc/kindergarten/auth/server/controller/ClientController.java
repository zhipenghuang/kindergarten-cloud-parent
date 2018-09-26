package com.zc.kindergarten.auth.server.controller;

import com.zc.kindergarten.auth.server.config.KeyConfiguration;
import com.zc.kindergarten.auth.server.service.AuthClientService;
import com.zc.kindergarten.common.msg.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private KeyConfiguration keyConfiguration;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<String> getAccessToken(String clientId, String secret) throws Exception {
        return new ResponseEntity(authClientService.apply(clientId, secret));
    }

    @RequestMapping(value = "/myClient")
    public ResponseEntity<List<String>> getAllowedClient(String serviceId, String secret) {
        return new ResponseEntity(authClientService.getAllowedClient(serviceId, secret));
    }

    @RequestMapping(value = "/servicePubKey", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new ResponseEntity(keyConfiguration.getServicePubKey());
    }

    @RequestMapping(value = "/userPubKey", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new ResponseEntity(keyConfiguration.getUserPubKey());
    }

}
