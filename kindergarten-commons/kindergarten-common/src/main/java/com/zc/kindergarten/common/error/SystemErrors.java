package com.zc.kindergarten.common.error;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public enum SystemErrors implements Errors {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 内部服务错误
     */
    SYSTEM_ERROR(500, "内部服务错误"),
    /**
     * 参数错误
     */
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(401, "参数错误"),
    /**
     * 请求处理出现异常
     */
    SERVLET_EXCEPTION(402, "请求处理出现异常"),
    /**
     * 请求参数格式或绑定错误
     */
    REQUEST_PARAM_ERROR(403, "请求参数格式或绑定错误");

    public int code;
    public String message;

    SystemErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMsg(int code) {
        for (SystemErrors errorCode : SystemErrors.values()) {
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
