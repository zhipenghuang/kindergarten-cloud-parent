package com.zc.kindergarten.common.vo;

import org.springframework.beans.BeanUtils;
import java.io.Serializable;

public class AbstractEntity implements Serializable {
	protected static long serialVersionUID = 1L;

	public AbstractEntity() {
	}

	public <T> T copyTo(Class<T> to) throws Exception {
		BeanUtils.copyProperties(this, to);
		return (T) to;
	}
}
