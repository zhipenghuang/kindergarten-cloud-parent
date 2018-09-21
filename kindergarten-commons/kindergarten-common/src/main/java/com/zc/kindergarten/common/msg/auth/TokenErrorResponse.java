package com.zc.kindergarten.common.msg.auth;


import com.zc.kindergarten.common.constant.RestCodeConstants;
import com.zc.kindergarten.common.msg.BaseResponse;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
