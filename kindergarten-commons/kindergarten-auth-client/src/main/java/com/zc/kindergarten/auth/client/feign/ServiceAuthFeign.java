package com.zc.kindergarten.auth.client.feign;

import com.zc.kindergarten.common.msg.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@FeignClient(value = "${auth.serviceId}", configuration = {})
public interface ServiceAuthFeign {

    @RequestMapping(value = "/client/myClient")
    ResponseEntity<List<String>> getAllowedClient(@RequestParam("serviceId") String serviceId, @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/token", method = RequestMethod.POST)
    ResponseEntity<String> getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/servicePubKey", method = RequestMethod.POST)
    ResponseEntity<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/userPubKey", method = RequestMethod.POST)
    ResponseEntity<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

}
