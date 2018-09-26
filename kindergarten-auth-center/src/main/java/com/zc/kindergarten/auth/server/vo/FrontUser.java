package com.zc.kindergarten.auth.server.vo;

import com.zc.kindergarten.common.vo.PermissionInfo;
import lombok.Data;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
public class FrontUser {

    public String id;
    public String username;
    public String name;
    private String description;
    private String image;
    private List<PermissionInfo> menus;
    private List<PermissionInfo> elements;
}
