package com.zc.kindergarten.auth.client.exception;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class JwtTokenExpiredException extends Exception {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
