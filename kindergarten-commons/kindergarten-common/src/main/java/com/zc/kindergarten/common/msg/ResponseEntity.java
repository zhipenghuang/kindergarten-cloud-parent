package com.zc.kindergarten.common.msg;

import com.zc.kindergarten.common.error.Errors;
import com.zc.kindergarten.common.error.SystemErrors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
@EqualsAndHashCode
@ToString
public class ResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 请求结果信息提示
	 */
	private String message;
	/**
	 * 时间戳
	 */
	private long ts;
	/**
	 * 返回数据编码，0成功 其他为失败
	 */
	private int ecode = 0;
	/**
	 * 返回请求数据{json}对象
	 */
	private T data;

	public ResponseEntity() {
		this.ecode = SystemErrors.SUCCESS.code;
		this.message = SystemErrors.SUCCESS.message;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(Errors errors) {
		this.ecode = errors.getCode();
		this.message = errors.getMessage();
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(int ecode, String message) {
		this.ecode = ecode;
		this.message = message;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(T t) {
		this.ecode = SystemErrors.SUCCESS.code;
		this.message = SystemErrors.SUCCESS.message;
		this.data = t;
		this.ts = System.currentTimeMillis();
	}
}
