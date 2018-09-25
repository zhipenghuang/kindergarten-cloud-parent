package com.zc.kindergarten.common.exception.auth;


import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.exception.BaseException;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ClientInvalidException extends BaseException {

    public ClientInvalidException() {
        super();
    }

    public ClientInvalidException(Errors error, Object data) {
        super(error, data);
    }

    public ClientInvalidException(Errors error) {
        super(error);
    }
}
