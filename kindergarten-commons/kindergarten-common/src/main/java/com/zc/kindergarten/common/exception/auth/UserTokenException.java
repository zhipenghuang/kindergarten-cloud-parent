package com.zc.kindergarten.common.exception.auth;


import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.exception.BaseException;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class UserTokenException extends BaseException {

	public UserTokenException() {
		super();
	}

	public UserTokenException(Errors error, String msg) {
		super(error, msg);
	}

	public UserTokenException(Errors error) {
		super(error);
	}
}
