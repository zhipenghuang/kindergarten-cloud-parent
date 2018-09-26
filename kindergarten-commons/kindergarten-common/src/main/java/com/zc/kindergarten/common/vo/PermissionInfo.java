package com.zc.kindergarten.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionInfo implements Serializable {

    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;
}
