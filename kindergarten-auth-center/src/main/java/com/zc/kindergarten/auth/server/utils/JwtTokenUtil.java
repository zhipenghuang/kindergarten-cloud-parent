package com.zc.kindergarten.auth.server.utils;

import com.zc.kindergarten.auth.server.config.KeyConfiguration;
import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import com.zc.kindergarten.common.utils.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IjwtInfo jwtInfo) throws Exception {
        return JwtHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(), expire);
    }

    public IjwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }


}
