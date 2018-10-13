package com.zc.kindergarten.gateway.fallback;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.vo.PermissionInfo;
import com.zc.kindergarten.gateway.feign.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author hzp
 * @create 2018/10/10.
 */
@Service
@Slf4j
public class UserServiceFallback implements IUserService {

    @Override
    public ResponseEntity<List<PermissionInfo>> getPermissionByUsername(@PathVariable("username") String username) {
        log.error("调用{}异常{}", "getPermissionByUsername", username);
        return null;
    }

    @Override
    public ResponseEntity<List<PermissionInfo>> getAllPermissionInfo() {
        log.error("调用{}异常", "getPermissionByUsername");
        return null;
    }
}
