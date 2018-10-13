package com.zc.kindergarten.common.error;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public enum AuthErrors implements Errors {
    /**
     * 客户端被禁止
     */
    CLIENT_FORBIDDEN(5001, "被拒绝的服务端"),
    /**
     * 客户端token过期
     */
    CLIENT_TOKEN_EXPIRED(5002, "该服务端token已过期"),
    /**
     * 该服务端token签名错误
     */
    CLIENT_TOKEN_SIGNATURE_ERROR(5003, "该服务端token签名错误"),
    /**
     * 服务端token为空或空字符串
     */
    CLIENT_TOKEN_IS_NULL_OR_EMPTY(5004, "该服务端token为空"),
    /**
     * 用户token过期
     */
    USER_TOKEN_EXPIRED(5005, "用户token已过期"),
    /**
     * 用户token签名错误
     */
    USER_TOKEN_SIGNATURE_ERROR(5006, "用户token签名错误"),
    /**
     * 用户token为空或空字符串
     */
    USER_TOKEN_IS_NULL_OR_EMPTY(5007, "用户token为空"),
    /**
     * 用户不存在或账户密码错误
     */
    USER_EXIST_OR_NAME_PASSWORD_ERROR(6001, "用户不存在或账户密码错误"),
    /**
     * client找不到或者client秘钥错误
     */
    CLIENT_NOT_FOUND_OR_CLIENT_SECRET_IS_ERROR(5008, "client找不到或者client秘钥错误"),
    /**
     * 该用户没有权限
     */
    USER_FORBIDDEN_DOES_NOT_HAS_PERMMISION(5009, "该用户没有权限");

    public int code;
    public String message;

    AuthErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMsg(int code) {
        for (AuthErrors errorCode : AuthErrors.values()) {
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
