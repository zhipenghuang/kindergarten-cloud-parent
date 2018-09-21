package com.zc.kindergarten.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class TreeNode {
    protected int id;
    protected int parentId;

    List<TreeNode> children = new ArrayList<TreeNode>();

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void add(TreeNode node){
        children.add(node);
    }
}
