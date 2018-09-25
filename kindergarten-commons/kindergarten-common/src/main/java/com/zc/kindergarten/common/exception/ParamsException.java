package com.zc.kindergarten.common.exception;

import com.zc.kindergarten.common.error.Errors;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ParamsException extends BaseException {


	public ParamsException() {
		super();
	}

	public ParamsException(Errors error, Object data) {
		super(error, data);
	}

	public ParamsException(Errors error) {
		super(error);
	}


}
