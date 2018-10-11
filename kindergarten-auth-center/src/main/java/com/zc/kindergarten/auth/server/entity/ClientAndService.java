package com.zc.kindergarten.auth.server.entity;

import lombok.Data;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
public class ClientAndService {

	protected Long id;

	private String serviceId;

	private String clientId;

	private String description;

	private String crtUser;

	private String crtName;

	private String crtHost;

}