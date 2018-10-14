package com.zc.kindergarten.common.exception;

import com.zc.kindergarten.common.error.Errors;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class RequestTimeOutException extends BaseException {

	public RequestTimeOutException() {
		super();
	}

	public RequestTimeOutException(Errors error, String msg) {
		super(error, msg);
	}

	public RequestTimeOutException(Errors error) {
		super(error);
	}
}
