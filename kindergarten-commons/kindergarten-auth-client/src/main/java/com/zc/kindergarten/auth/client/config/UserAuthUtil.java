package com.zc.kindergarten.auth.client.config;

import com.zc.kindergarten.common.exception.auth.UserTokenException;
import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import com.zc.kindergarten.common.utils.jwt.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
public class UserAuthUtil {
    @Autowired
    private UserAuthConfig userAuthConfig;
    public IjwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, userAuthConfig.getPubKeyByte());
        }catch (ExpiredJwtException ex){
            throw new UserTokenException("User token expired!");
        }catch (SignatureException ex){
            throw new UserTokenException("User token signature error!");
        }catch (IllegalArgumentException ex){
            throw new UserTokenException("User token is null or empty!");
        }
    }
}
