package com.zc.kindergarten.usercenter.service;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.usercenter.entity.Menu;
import com.zc.kindergarten.usercenter.vo.AuthorityMenuTree;
import com.zc.kindergarten.usercenter.vo.MenuTree;

import java.util.List;

public interface MenuService {
	List<Menu> selectByTitle(String title);

	List<MenuTree> selectMenuTreeByTitle(String title);

	List<Menu> selectList();

	List<MenuTree> selectById(Long parentId);

	List<AuthorityMenuTree> selectListAll();

	List<MenuTree> getUserAuthorityMenuByUserIdAndParentId(Long userId, Long parentId);

	List<Menu> getUserAuthoritySystemByUserId(Long userId);
}
