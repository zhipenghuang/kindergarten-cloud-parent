package com.zc.kindergarten.usercenter.entity;

import com.zc.kindergarten.common.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ResourceAuthority extends BaseEntity {
    private Long id;

    private String authorityId;

    private String authorityType;

    private String resourceId;

    private String resourceType;

    private String parentId;

    private String path;

    private String description;

    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;


    public ResourceAuthority(String authorityType, String resourceType) {
        this.authorityType = authorityType;
        this.resourceType = resourceType;
    }

    public ResourceAuthority() {
    }
}