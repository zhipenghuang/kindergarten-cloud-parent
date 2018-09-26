package com.zc.kindergarten.common.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hzp
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 6213490598973169955L;

    public String id;
    public String username;
    public String password;
    public String name;
    private String description;
    private Date updTime;

}
