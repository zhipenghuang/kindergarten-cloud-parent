package com.zc.kindergarten.usercenter.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String birthday;

    private String address;

    private String mobilePhone;

    private String telPhone;

    private String email;

    private String sex;

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
}