package com.zc.kindergarten.common.exception.auth;

import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.exception.BaseException;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ClientTokenException extends BaseException {

	public ClientTokenException() {
		super();
	}

	public ClientTokenException(Errors error, String msg) {
		super(error, msg);
	}

	public ClientTokenException(Errors error) {
		super(error);
	}
}
