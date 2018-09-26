package com.zc.kindergarten.auth.server.service;

import com.zc.kindergarten.auth.server.vo.request.JwtAuthenticationRequest;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public interface AuthService {

    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;

    String refresh(String oldToken) throws Exception;

    void validate(String token) throws Exception;
}
