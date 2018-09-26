package com.zc.kindergarten.auth.server.runner;

import com.zc.kindergarten.auth.server.config.KeyConfiguration;
import com.zc.kindergarten.common.utils.jwt.RsaKeyHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
@Slf4j
public class AuthServerRunner implements CommandLineRunner {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String REDIS_USER_PRI_KEY = "ZC:AUTH:JWT:PRI";
    private static final String REDIS_USER_PUB_KEY = "ZC:AUTH:JWT:PUB";
    private static final String REDIS_SERVICE_PRI_KEY = "ZC:AUTH:CLIENT:PRI";
    private static final String REDIS_SERVICE_PUB_KEY = "ZC:AUTH:CLIENT:PUB";

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始设置秘钥");
        if (redisTemplate.hasKey(REDIS_USER_PRI_KEY)&&redisTemplate.hasKey(REDIS_USER_PUB_KEY)&&redisTemplate.hasKey(REDIS_SERVICE_PRI_KEY)&&redisTemplate.hasKey(REDIS_SERVICE_PUB_KEY)) {
            keyConfiguration.setUserPriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PRI_KEY)));
            keyConfiguration.setUserPubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PUB_KEY)));
            keyConfiguration.setServicePriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PRI_KEY)));
            keyConfiguration.setServicePubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PUB_KEY)));
        } else {
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getUserSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
            keyMap = RsaKeyHelper.generateKey(keyConfiguration.getServiceSecret());
            keyConfiguration.setServicePriKey(keyMap.get("pri"));
            keyConfiguration.setServicePubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));

        }
    }
}
