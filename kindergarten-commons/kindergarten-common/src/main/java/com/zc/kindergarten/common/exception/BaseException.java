package com.zc.kindergarten.common.exception;

import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.error.SystemErrors;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class BaseException extends RuntimeException implements ExceptionScalable {
	private static final long serialVersionUID = 5692243177785821696L;

	private Errors error;
	private Object data;

	public BaseException() {
	}

	public BaseException(Errors error) {
		this.error = error;
	}

	public BaseException(Errors error, Object data) {
		this.error = error;
		this.data = data;
	}

	public BaseException(Errors error, String msg, Object data) {
		this.error = error;
		this.error.setMessage(msg);
		this.data = data;
	}

	public Errors getError() {
		return error;
	}

	public Object getData() {
		return data;
	}
}
