package com.zc.kindergarten.usercenter.service;

import com.zc.kindergarten.common.vo.FrontUser;
import com.zc.kindergarten.common.vo.PermissionInfo;
import com.zc.kindergarten.common.vo.UserInfo;
import com.zc.kindergarten.usercenter.entity.User;
import com.zc.kindergarten.usercenter.vo.MenuTree;

import java.util.List;

public interface UserService {

	User getUserByUsername(String currentUserName);

	FrontUser getUserInfo(String token) throws Exception;

	List<MenuTree> getMenusByUsername(String token) throws Exception;

	List<PermissionInfo> getAllPermission();

	List<PermissionInfo> getPermissionByUsername(String username);

	UserInfo validate(String username, String password);
}
