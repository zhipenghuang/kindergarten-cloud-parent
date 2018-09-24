package com.zc.kindergarten.common.error;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public enum Errors {

	SUCCESS(Integer.valueOf(0), "操作成功");

	public int code;
	public String label;

	Errors(Integer code, String label) {
		this.code = code.intValue();
		this.label = label;
	}

	public static String getMessage(int code) {
		String result = "";
		Errors[] var2 = values();
		int var3 = var2.length;

		for(int var4 = 0; var4 < var3; ++var4) {
			Errors status = var2[var4];
			if(status.code == code) {
				result = status.label;
			}
		}

		return result;
	}
}
