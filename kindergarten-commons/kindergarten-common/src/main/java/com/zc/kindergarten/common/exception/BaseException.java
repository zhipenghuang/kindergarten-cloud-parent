package com.zc.kindergarten.common.exception;

import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.error.SystemErrors;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class BaseException extends RuntimeException implements ExceptionScalable {

    private Errors error;
    private Object data;

    public BaseException() {
    }

    public BaseException(Errors error) {
        super(error.getMessage());
        this.error = error;
    }

    public BaseException(Errors error, Object data) {
        super(error.getMessage());
        this.error = error;
        this.data = data;
    }

    public BaseException(Errors error, String msg, Object data) {
        super(msg);
        this.error = error;
        this.error.setMessage(msg);
        this.data = data;
    }

    @Override
    public Errors getError() {
        return error;
    }

    @Override
    public Object getData() {
        return data;
    }

}
