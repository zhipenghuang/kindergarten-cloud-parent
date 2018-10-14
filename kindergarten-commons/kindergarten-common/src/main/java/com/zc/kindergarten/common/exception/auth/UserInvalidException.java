package com.zc.kindergarten.common.exception.auth;


import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.exception.BaseException;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class UserInvalidException extends BaseException {

    public UserInvalidException() {
        super();
    }

    public UserInvalidException(Errors error, String msg) {
        super(error, msg);
    }

    public UserInvalidException(Errors error) {
        super(error);
    }
}
