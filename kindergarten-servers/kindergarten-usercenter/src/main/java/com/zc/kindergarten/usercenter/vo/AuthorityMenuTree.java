package com.zc.kindergarten.usercenter.vo;

import com.zc.kindergarten.common.util.TreeNode;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-19 13:03
 */
@Data
public class AuthorityMenuTree extends TreeNode implements Serializable {
	String text;
	List<AuthorityMenuTree> nodes = new ArrayList();
	String icon;


	public AuthorityMenuTree(String text, List<AuthorityMenuTree> nodes) {
		this.text = text;
		this.nodes = nodes;
	}

	public AuthorityMenuTree() {
	}

	@Override
	public void setChildren(List<TreeNode> children) {
		super.setChildren(children);
		nodes = new ArrayList();
	}

	@Override
	public void add(TreeNode node) {
		super.add(node);
		AuthorityMenuTree n = new AuthorityMenuTree();
		BeanUtils.copyProperties(node, n);
		nodes.add(n);
	}
}
