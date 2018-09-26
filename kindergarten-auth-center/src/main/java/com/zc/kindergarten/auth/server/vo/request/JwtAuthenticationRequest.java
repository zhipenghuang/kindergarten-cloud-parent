package com.zc.kindergarten.auth.server.vo.request;

import lombok.Data;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
public class JwtAuthenticationRequest{

    private String username;
    private String password;

    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtAuthenticationRequest() {
    }
}
