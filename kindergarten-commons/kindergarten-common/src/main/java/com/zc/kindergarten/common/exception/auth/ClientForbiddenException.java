package com.zc.kindergarten.common.exception.auth;

import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.exception.BaseException;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ClientForbiddenException extends BaseException {

	public ClientForbiddenException() {
		super();
	}

	public ClientForbiddenException(Errors error, String msg) {
		super(error, msg);
	}

	public ClientForbiddenException(Errors error) {
		super(error);
	}
}
