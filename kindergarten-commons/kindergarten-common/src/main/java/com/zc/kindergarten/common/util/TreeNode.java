package com.zc.kindergarten.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class TreeNode {
    protected Long id;
    protected Long parentId;

    List<TreeNode> children = new ArrayList<TreeNode>();

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void add(TreeNode node){
        children.add(node);
    }
}
