package com.zc.kindergarten.auth.server.service.impl;

import com.zc.kindergarten.auth.server.feign.IUserService;
import com.zc.kindergarten.auth.server.service.AuthService;
import com.zc.kindergarten.auth.server.utils.JwtTokenUtil;
import com.zc.kindergarten.auth.server.vo.request.JwtAuthenticationRequest;
import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.exception.auth.UserInvalidException;
import com.zc.kindergarten.common.utils.jwt.JwtInfo;
import com.zc.kindergarten.common.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author hzp
 * @create 2018/9/19.
 */

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private IUserService userService;

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = userService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            return jwtTokenUtil.generateToken(new JwtInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        throw new UserInvalidException(AuthErrors.USER_EXIST_OR_NAME_PASSWORD_ERROR);
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public String refresh(String oldToken) throws Exception {
        return jwtTokenUtil.generateToken(jwtTokenUtil.getInfoFromToken(oldToken));
    }
}
