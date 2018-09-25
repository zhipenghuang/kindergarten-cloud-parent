package com.zc.kindergarten.common.error;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public enum SystemErrors implements Errors {

	SUCCESS(0, "操作成功"),
	SYSTEM_ERROR(500, "内部服务错误"),
	METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(401, "参数错误"),
	SERVLET_EXCEPTION(402, "请求处理出现异常"),
	REQUEST_PARAM_ERROR(403,"请求参数格式或绑定错误");

	public int code;
	public String message;

	SystemErrors(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	SystemErrors(Integer code) {
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
