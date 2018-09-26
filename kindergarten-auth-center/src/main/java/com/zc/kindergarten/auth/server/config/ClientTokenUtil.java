package com.zc.kindergarten.auth.server.config;

import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import com.zc.kindergarten.common.utils.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
public class ClientTokenUtil {

    @Value("${client.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IjwtInfo jwtInfo) throws Exception {
        return JwtHelper.generateToken(jwtInfo, keyConfiguration.getServicePriKey(), expire);
    }

    public IjwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token, keyConfiguration.getServicePubKey());
    }

}
