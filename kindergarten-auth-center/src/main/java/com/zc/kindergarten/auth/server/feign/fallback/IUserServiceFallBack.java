package com.zc.kindergarten.auth.server.feign.fallback;

import com.zc.kindergarten.auth.server.feign.IUserService;
import com.zc.kindergarten.auth.server.vo.request.JwtAuthenticationRequest;
import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IUserServiceFallBack implements IUserService {
    @Override
    public ResponseEntity<UserInfo> validate(JwtAuthenticationRequest authenticationRequest) {
        log.error("调用{}异常{}{}", "validate", authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return null;
    }
}
