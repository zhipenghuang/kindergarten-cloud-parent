package com.zc.kindergarten.auth.server.vo.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponse{

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
