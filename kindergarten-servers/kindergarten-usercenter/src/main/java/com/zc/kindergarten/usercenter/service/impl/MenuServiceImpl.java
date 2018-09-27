package com.zc.kindergarten.usercenter.service.impl;

import com.zc.kindergarten.common.util.TreeUtil;
import com.zc.kindergarten.usercenter.constant.AdminCommonConstant;
import com.zc.kindergarten.usercenter.entity.Menu;
import com.zc.kindergarten.usercenter.mapper.MenuMapper;
import com.zc.kindergarten.usercenter.service.MenuService;
import com.zc.kindergarten.usercenter.vo.AuthorityMenuTree;
import com.zc.kindergarten.usercenter.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> selectByTitle(String title) {
		Example example = new Example(Menu.class);
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andLike("title", "%" + title + "%");
		}
		List<Menu> menuList = menuMapper.selectByExample(example);
		return menuList;
	}

	@Override
	public List<MenuTree> selectMenuTreeByTitle(String title) {
		Example example = new Example(Menu.class);
		if (StringUtils.isNotBlank(title)) {
			example.createCriteria().andLike("title", "%" + title + "%");
		}
		List<Menu> menuList = menuMapper.selectByExample(example);
		List<MenuTree> menuTree = getMenuTree(menuList, AdminCommonConstant.ROOT);
		return menuTree;
	}

	private List<MenuTree> getMenuTree(List<Menu> menus, Long root) {
		List<MenuTree> trees = new ArrayList<MenuTree>();
		MenuTree node = null;
		for (Menu menu : menus) {
			node = new MenuTree();
			BeanUtils.copyProperties(menu, node);
			node.setLabel(menu.getTitle());
			trees.add(node);
		}
		return TreeUtil.bulid(trees, root);
	}

	@Override
	public List<Menu> selectList() {
		Menu menu = new Menu();
		menu.setParentId(AdminCommonConstant.ROOT);
		List<Menu> list = menuMapper.select(menu);
		return list;
	}

	@Override
	public List<MenuTree> selectById(Long parentId) {
		Menu parent = menuMapper.selectByPrimaryKey(parentId);
		Example example = new Example(Menu.class);
		example.createCriteria().andLike("path", parent.getPath() + "%").andNotEqualTo("id", parent.getId());
		List<Menu> menuList = menuMapper.selectByExample(example);
		List<MenuTree> menuTree = getMenuTree(menuList, parent.getId());
		return menuTree;
	}

	@Override
	public List<AuthorityMenuTree> selectListAll() {
		List<AuthorityMenuTree> trees = new ArrayList();
		AuthorityMenuTree node = null;
		List<Menu> menuList = menuMapper.selectAll();
		for (Menu menu : menuList) {
			node = new AuthorityMenuTree();
			node.setText(menu.getTitle());
			BeanUtils.copyProperties(menu, node);
			trees.add(node);
		}
		List<AuthorityMenuTree> authorityMenuTrees = TreeUtil.bulid(trees, AdminCommonConstant.ROOT);
		return authorityMenuTrees;
	}

	@Override
	public List<MenuTree> getUserAuthorityMenuByUserIdAndParentId(Long userId, Long parentId) {
		List<Menu> menuList = menuMapper.selectAuthorityMenuByUserId(userId);
		List<MenuTree> menuTreeList = getMenuTree(menuList, parentId);
		return menuTreeList;
	}

	@Override
	public List<Menu> getUserAuthoritySystemByUserId(Long userId) {

		List<Menu> menuList = menuMapper.selectAuthoritySystemByUserId(userId);
		return menuList;
	}

}
