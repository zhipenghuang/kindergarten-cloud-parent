package com.zc.kindergarten.common.error;

public enum UploadErrors implements Errors{

	/**
	 * 该用户没有权限
	 */
	FILE_UPLOAD_FAIL(7001, "文件上传失败"),
	FILE_DOESNOT_NULL(7002,"文件不能为空");

	public int code;
	public String message;

	UploadErrors(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static String getMsg(int code) {
		for (UploadErrors errorCode : UploadErrors.values()) {
			if (code == errorCode.getCode()) {
				return errorCode.getMessage();
			}
		}
		return null;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String msg) {
		this.message = msg;
	}
}
