package com.zc.kindergarten.usercenter.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import com.zc.kindergarten.usercenter.constant.AdminCommonConstant;
import lombok.Data;

import java.util.Date;

@Data
public class Menu extends BaseEntity {
    private Long id;

    private String code;

    private String title;

    private Long parentId = AdminCommonConstant.ROOT;

    private String href;

    private String icon;

    private String type;

    private String description;

    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;

    private Date updTime;

    private String updUser;

    private String updName;

    private String updHost;

	private String path;
}