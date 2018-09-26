
package com.zc.kindergarten.auth.client.config;

import com.zc.kindergarten.auth.client.feign.ServiceAuthFeign;
import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.exception.auth.ClientTokenException;
import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.common.utils.jwt.IjwtInfo;
import com.zc.kindergarten.common.utils.jwt.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Configuration
@Slf4j
@EnableScheduling
public class ServiceAuthUtil {

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    private List<String> allowedClient;
    private String clientToken;


    public IjwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyByte());
        } catch (ExpiredJwtException ex) {
            throw new ClientTokenException(AuthErrors.CLIENT_TOKEN_EXPIRED);
        } catch (SignatureException ex) {
            throw new ClientTokenException(AuthErrors.CLIENT_TOKEN_SIGNATURE_ERROR);
        } catch (IllegalArgumentException ex) {
            throw new ClientTokenException(AuthErrors.CLIENT_TOKEN_IS_NULL_OR_EMPTY);
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient() {
        log.debug("refresh allowedClient.....");
        ResponseEntity<List<String>> resp = serviceAuthFeign.getAllowedClient(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getEcode() == 0) {
            this.allowedClient = resp.getData();
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken() {
        log.debug("refresh client token.....");
        ResponseEntity<String> resp = serviceAuthFeign.getAccessToken(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getEcode() == 0) {
            this.clientToken = resp.getData();
        }
    }


    public String getClientToken() {
        if (this.clientToken == null) {
            this.refreshClientToken();
        }
        return clientToken;
    }

    public List<String> getAllowedClient() {
        if (this.allowedClient == null) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }
}