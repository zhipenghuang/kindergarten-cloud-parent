package com.zc.kindergarten.usercenter.service.impl;

import com.zc.kindergarten.auth.client.config.UserAuthUtil;
import com.zc.kindergarten.common.constant.CommonConstants;
import com.zc.kindergarten.common.util.TreeUtil;
import com.zc.kindergarten.common.vo.FrontUser;
import com.zc.kindergarten.common.vo.PermissionInfo;
import com.zc.kindergarten.common.vo.UserInfo;
import com.zc.kindergarten.usercenter.constant.AdminCommonConstant;
import com.zc.kindergarten.usercenter.entity.Element;
import com.zc.kindergarten.usercenter.entity.Menu;
import com.zc.kindergarten.usercenter.entity.User;
import com.zc.kindergarten.usercenter.mapper.ElementMapper;
import com.zc.kindergarten.usercenter.mapper.MenuMapper;
import com.zc.kindergarten.usercenter.mapper.UserMapper;
import com.zc.kindergarten.usercenter.service.UserService;
import com.zc.kindergarten.usercenter.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private ElementMapper elementMapper;
	@Autowired
	private UserAuthUtil userAuthUtil;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	@Override
	public User getUserByUsername(String currentUserName) {
		User user = new User();
		user.setUsername(currentUserName);
		return userMapper.selectOne(user);
	}
	@Override
	public FrontUser getUserInfo(String token) throws Exception {
		String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
		if (username == null) {
			return null;
		}
		UserInfo info = new UserInfo();
		User user = getUserByUsername(username);
		BeanUtils.copyProperties(user, info);
		info.setId(user.getId().toString());
		FrontUser frontUser = new FrontUser();
		BeanUtils.copyProperties(info, frontUser);
		List<PermissionInfo> permissionInfos = getPermissionByUsername(username);
		Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
			return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
		});
		frontUser.setMenus(menus.collect(Collectors.toList()));
		Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
			return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
		});
		frontUser.setElements(elements.collect(Collectors.toList()));
		return frontUser;
	}

	@Override
	public List<MenuTree> getMenusByUsername(String token) throws Exception {
		String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
		if (username == null) {
			return null;
		}
		User user = getUserByUsername(username);
		List<Menu> menus = menuMapper.selectAuthorityMenuByUserId(user.getId());
		return getMenuTree(menus,AdminCommonConstant.ROOT);
	}

	@Override
	public List<PermissionInfo> getAllPermission() {
		List<Menu> menus = menuMapper.selectAll();
		List<PermissionInfo> result = new ArrayList<>();
		PermissionInfo info = null;
		menu2permission(menus, result);
		List<Element> elements = elementMapper.selectAllElementPermissions();
		element2permission(result, elements);
		return result;
	}

	private List<MenuTree> getMenuTree(List<Menu> menus, Long root) {
		List<MenuTree> trees = new ArrayList();
		MenuTree node = null;
		for (Menu menu : menus) {
			node = new MenuTree();
			BeanUtils.copyProperties(menu, node);
			trees.add(node);
		}
		return TreeUtil.bulid(trees, root);
	}

	@Override
    public List<PermissionInfo> getPermissionByUsername(String username) {
		User user = getUserByUsername(username);
		List<Menu> menus = menuMapper.selectAuthorityMenuByUserId(user.getId());
		List<PermissionInfo> result = new ArrayList<>();
		PermissionInfo info = null;
		menu2permission(menus, result);
		List<Element> elements = elementMapper.selectAuthorityElementByUserId(user.getId() + "");
		element2permission(result, elements);
		return result;
	}

	@Override
	public UserInfo validate(String username,String password){
		UserInfo info = new UserInfo();
		User user = getUserByUsername(username);
		if (encoder.matches(password, user.getPassword())) {
			BeanUtils.copyProperties(user, info);
			info.setId(user.getId().toString());
		}
		return info;
	}

	private void element2permission(List<PermissionInfo> result, List<Element> elements) {
		PermissionInfo info;
		for (Element element : elements) {
			info = new PermissionInfo();
			info.setCode(element.getCode());
			info.setType(element.getType());
			info.setUri(element.getUri());
			info.setMethod(element.getMethod());
			info.setName(element.getName());
			info.setMenu(element.getMenuId());
			result.add(info);
		}
	}
	private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
		PermissionInfo info;
		for (Menu menu : menus) {
			if (StringUtils.isBlank(menu.getHref())) {
				menu.setHref("/" + menu.getCode());
			}
			info = new PermissionInfo();
			info.setCode(menu.getCode());
			info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
			info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
			String uri = menu.getHref();
			if (!uri.startsWith("/")) {
				uri = "/" + uri;
			}
			info.setUri(uri);
			info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
			result.add(info
			);
			info.setMenu(menu.getTitle());
		}
	}
}
