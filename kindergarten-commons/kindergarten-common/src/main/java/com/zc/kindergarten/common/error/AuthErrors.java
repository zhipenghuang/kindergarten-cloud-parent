package com.zc.kindergarten.common.error;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public enum AuthErrors implements Errors {

	CLIENT_FORBIDDEN(5001, "被拒绝的服务端"),
	CLIENT_TOKEN_EXPIRED(5002, "该服务端token已过期"),
	CLIENT_TOKEN_SIGNATURE_ERROR(5003, "该服务端token签名错误"),
	CLIENT_TOKEN_IS_NULL_OR_EMPTY(5004, "该服务端token为空"),
	USER_TOKEN_EXPIRED(5005, "token已过期"),
	USER_TOKEN_SIGNATURE_ERROR(5006, "token签名错误"),
	USER_TOKEN_IS_NULL_OR_EMPTY(5007, "token为空");

	public int code;
	public String message;

	AuthErrors(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	AuthErrors(Integer code) {
		this.code = code;
	}

	public static String getMsg(int code) {
		for (SystemErrors errorCode : SystemErrors.values()) {
			if (code == errorCode.getCode()) {
				return errorCode.getMessage();
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
}
