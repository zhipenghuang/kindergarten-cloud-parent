package com.zc.kindergarten.usercenter.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Element {

	private Long id;

	private String code;

	private String type;

	private String name;

	private String uri;

	private String menuId;

	private String parentId;

	private String path;

	private String method;

	private String description;

	private Date crtTime;

	private String crtUser;

	private String crtName;

	private String crtHost;
}