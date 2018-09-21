package com.zc.kindergarten.auth.client.exception;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
