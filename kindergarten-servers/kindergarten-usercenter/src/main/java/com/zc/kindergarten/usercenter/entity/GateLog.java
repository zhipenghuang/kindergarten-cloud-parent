package com.zc.kindergarten.usercenter.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GateLog {

	private Long id;

	private String menu;

	private String opt;

	private String uri;

	private Date crtTime;

	private String crtUser;

	private String crtName;

	private String crtHost;

}