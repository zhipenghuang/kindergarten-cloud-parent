package com.zc.kindergarten.usercenter.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import com.zc.kindergarten.usercenter.constant.AdminCommonConstant;
import lombok.Data;

import java.util.Date;

@Data
public class Group {
    private Long id;

    private String code;

    private String name;

    private Integer parentId;

    private String path;

    private String type;

    private Integer groupType = AdminCommonConstant.DEFAULT_GROUP_TYPE;

    private String description;

    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;

    private Date updTime;

    private String updUser;

    private String updName;

    private String updHost;

}