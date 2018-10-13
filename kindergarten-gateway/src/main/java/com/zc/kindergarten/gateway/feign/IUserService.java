package com.zc.kindergarten.gateway.feign;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.PermissionInfo;
import com.zc.kindergarten.gateway.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2018-10-21 8:11
 */
@FeignClient(value = "kindergarten-usercenter", fallback = UserServiceFallback.class)
public interface IUserService {
    /**
     *  根据用户名获取权限
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    ResponseEntity<List<PermissionInfo>> getPermissionByUsername(@PathVariable("username") String username);
    /**
     *  获取所有权限
     * @param
     * @return
     */
    @RequestMapping(value = "/user/permissions", method = RequestMethod.GET)
    ResponseEntity<List<PermissionInfo>> getAllPermissionInfo();
}
