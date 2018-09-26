package com.zc.kindergarten.auth.server.mapper;

import com.zc.kindergarten.auth.server.entity.AuthClient;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public interface AuthClientMapper extends Mapper<AuthClient> {

    List<String> selectAllowedClient(String serviceId);

    List<AuthClient> selectAuthorityServiceInfo(int clientId);
}
