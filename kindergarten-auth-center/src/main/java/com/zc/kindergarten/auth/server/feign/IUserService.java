package com.zc.kindergarten.auth.server.feign;

import com.zc.kindergarten.auth.server.config.FeignConfiguration;
import com.zc.kindergarten.auth.server.vo.request.JwtAuthenticationRequest;
import com.zc.kindergarten.common.vo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2018-09-26
 */
@FeignClient(value = "kindergarten-usercenter", configuration = FeignConfiguration.class)
public interface IUserService {

    /**
     * 根据用户名密码查询用户
     *
     * @param authenticationRequest
     * @return UserInfo
     */
    @RequestMapping(value = "/user/validate", method = RequestMethod.POST)
    UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}
