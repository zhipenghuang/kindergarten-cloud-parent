package com.zc.kindergarten.usercenter.service;

import com.zc.kindergarten.usercenter.entity.Group;
import com.zc.kindergarten.usercenter.vo.AuthorityMenuTree;
import com.zc.kindergarten.usercenter.vo.GroupTree;
import com.zc.kindergarten.usercenter.vo.GroupUsers;

import java.util.List;

public interface GroupService {
	List<Group> selectListByNameAndGroupType(String name, String groupType);

	void modifyGroupUsers(int id, String members, String leaders);

	GroupUsers getGroupUsers(int id);

	void modifyAuthorityMenu(int id, String[] menus);

	List<AuthorityMenuTree>  getAuthorityMenu(int id);

	void modifyAuthorityElement(int id, int menuId, int elementId);

	void removeAuthorityElement(int id, int menuId, int elementId);

	List<Integer> getAuthorityElement(int id);

	List<GroupTree> selectByNameAndGroupType(String name, String groupType);
}
