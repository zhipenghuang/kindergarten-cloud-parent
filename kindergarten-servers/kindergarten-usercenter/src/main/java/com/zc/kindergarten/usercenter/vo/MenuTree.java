package com.zc.kindergarten.usercenter.vo;

import com.zc.kindergarten.common.util.TreeNode;
import lombok.Data;

/**
 * Created by Ace on 2017/6/12.
 */
@Data
public class MenuTree extends TreeNode {
    String icon;
    String title;
    String href;
    boolean spread = false;
    String path;
    String component;
    String authority;
    String redirect;
    String code;
    String label;

    public MenuTree() {
    }

    public MenuTree(Long id, String name, Long parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
        this.label = name;
    }
    public MenuTree(Long id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
        this.label = name;
    }
}
