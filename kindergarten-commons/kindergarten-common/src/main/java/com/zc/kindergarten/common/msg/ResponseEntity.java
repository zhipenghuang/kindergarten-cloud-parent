package com.zc.kindergarten.common.msg;

import com.zc.kindergarten.common.error.Errors;
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
public class ResponseEntity<T2> implements Serializable {

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
	private T2 data;

	public ResponseEntity() {
		this.ecode = Errors.SUCCESS.code;
		this.message = Errors.SUCCESS.label;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(Errors errors) {
		this.ecode = errors.code;
		this.message = errors.label;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(int ecode, String message) {
		this.ecode = ecode;
		this.message = message;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(T2 t2) {
		this.ecode = Errors.SUCCESS.code;
		this.message = Errors.SUCCESS.label;
		this.data = t2;
		this.ts = System.currentTimeMillis();
	}

	public void setErrorType(String errorType) {
		this.ecode = Errors.valueOf(errorType).code;
		this.message = Errors.valueOf(errorType).label;
		this.ts = System.currentTimeMillis();
	}
}
