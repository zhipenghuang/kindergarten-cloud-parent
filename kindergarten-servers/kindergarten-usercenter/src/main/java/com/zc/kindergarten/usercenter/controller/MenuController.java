package com.zc.kindergarten.usercenter.controller;

import com.zc.kindergarten.common.context.BaseContextHandler;
import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.usercenter.entity.Menu;
import com.zc.kindergarten.usercenter.service.MenuService;
import com.zc.kindergarten.usercenter.service.UserService;
import com.zc.kindergarten.usercenter.vo.AuthorityMenuTree;
import com.zc.kindergarten.usercenter.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-12 8:49
 */
@Controller
@RequestMapping("menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Menu>> list(String title) {

		List<Menu> menuList = menuService.selectByTitle(title);
		return new ResponseEntity<>(menuList);
	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MenuTree>> getTree(String title) {

		List<MenuTree> menuTrees = menuService.selectMenuTreeByTitle(title);
		return new ResponseEntity<>(menuTrees);
	}


	@RequestMapping(value = "/system", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Menu>> getSystem() {
		List<Menu> menuList = menuService.selectList();
		return new ResponseEntity<>(menuList);
	}

	@RequestMapping(value = "/menuTree", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MenuTree>> listMenu(Long parentId) {
		try {
			if (parentId == null) {
				parentId = getSystem().getData().get(0).getId();
			}
		} catch (Exception e) {
			ArrayList<MenuTree> menuTrees = new ArrayList<>();
			return new ResponseEntity<>(menuTrees);
		}
		List<MenuTree> menuTreeList = menuService.selectById(parentId);
		return new ResponseEntity<>(menuTreeList);
	}

	@RequestMapping(value = "/authorityTree", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AuthorityMenuTree>> listAuthorityMenu() {
		List<AuthorityMenuTree> authorityMenuTrees = menuService.selectListAll();
		return new ResponseEntity<>(authorityMenuTrees);
	}

	@RequestMapping(value = "/user/authorityTree", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MenuTree>> listUserAuthorityMenu(Long parentId) {
		Long userId = userService.getUserByUsername(getCurrentUserName()).getId();
		try {
			if (parentId == null) {
				parentId = getSystem().getData().get(0).getId();
			}
		} catch (Exception e) {
			return new ResponseEntity(new ArrayList<MenuTree>());
		}
		List<MenuTree> menuTreeList = menuService.getUserAuthorityMenuByUserIdAndParentId(userId, parentId);
		return new ResponseEntity<>(menuTreeList);
	}

	@RequestMapping(value = "/user/system", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Menu>> listUserAuthoritySystem() {
		Long userId = userService.getUserByUsername(getCurrentUserName()).getId();
		List<Menu> menuList = menuService.getUserAuthoritySystemByUserId(userId);
		return new ResponseEntity<>(menuList);
	}

	public String getCurrentUserName() {
		return BaseContextHandler.getUsername();
	}
}
