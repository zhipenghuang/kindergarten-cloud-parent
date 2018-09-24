package com.zc.kindergarten.common.entity;

import java.beans.ConstructorProperties;
import java.util.Date;
import com.zc.kindergarten.common.vo.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class BaseEntity extends AbstractEntity {
	protected Long id;
	protected Date createdAt;
	protected Byte delFlag;
	protected String remarks;
	protected Date updatedAt;

	public BaseEntity() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
		this.delFlag = Byte.valueOf((byte) 0);
		this.remarks = "";
	}

	protected boolean canEqual(Object other) {
		return other instanceof BaseEntity;
	}

	@ConstructorProperties({"id", "createdAt", "delFlag", "remarks", "updatedAt"})
	public BaseEntity(Long id, Date createdAt, Byte delFlag, String remarks, Date updatedAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.delFlag = delFlag;
		this.remarks = remarks;
		this.updatedAt = updatedAt;
	}
}

