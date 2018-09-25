package com.zc.kindergarten.auth.server.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import lombok.Data;
import java.util.Date;
/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
public class AuthClient extends BaseEntity {

	private String code;

	private String secret;

	private String name;

	private String locked = "0";

	private String description;

	private String crtUser;

	private String crtName;

	private String crtHost;

	private String updUser;

	private String updName;

	private String updHost;
}
