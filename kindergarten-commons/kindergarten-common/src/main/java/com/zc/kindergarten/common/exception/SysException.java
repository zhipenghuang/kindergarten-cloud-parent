package com.zc.kindergarten.common.exception;

import com.zc.kindergarten.common.error.Errors;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class SysException extends BaseException {

	public SysException() {
		super();
	}

	public SysException(Errors error, Object data) {
		super(error, data);
	}

	public SysException(Errors error) {
		super(error);
	}
}
