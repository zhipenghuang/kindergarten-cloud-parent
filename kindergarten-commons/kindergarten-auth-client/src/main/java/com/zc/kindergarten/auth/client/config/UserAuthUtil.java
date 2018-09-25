package com.zc.kindergarten.auth.client.config;

import com.zc.kindergarten.common.error.AuthErrors;
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
		} catch (ExpiredJwtException ex) {
			throw new UserTokenException(AuthErrors.USER_TOKEN_EXPIRED);
		} catch (SignatureException ex) {
			throw new UserTokenException(AuthErrors.USER_TOKEN_SIGNATURE_ERROR);
		} catch (IllegalArgumentException ex) {
			throw new UserTokenException(AuthErrors.USER_TOKEN_IS_NULL_OR_EMPTY);
		}
	}
}
