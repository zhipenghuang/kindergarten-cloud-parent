package com.zc.kindergarten.usercenter.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class GroupType extends BaseEntity {
    private Long id;

    private String code;

    private String name;

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