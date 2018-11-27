package com.zc.kindergarten.usercenter.service.impl;

import com.zc.kindergarten.common.util.TreeUtil;
import com.zc.kindergarten.usercenter.constant.AdminCommonConstant;
import com.zc.kindergarten.usercenter.entity.Group;
import com.zc.kindergarten.usercenter.entity.Menu;
import com.zc.kindergarten.usercenter.entity.ResourceAuthority;
import com.zc.kindergarten.usercenter.mapper.GroupMapper;
import com.zc.kindergarten.usercenter.mapper.MenuMapper;
import com.zc.kindergarten.usercenter.mapper.ResourceAuthorityMapper;
import com.zc.kindergarten.usercenter.mapper.UserMapper;
import com.zc.kindergarten.usercenter.service.GroupService;
import com.zc.kindergarten.usercenter.vo.AuthorityMenuTree;
import com.zc.kindergarten.usercenter.vo.GroupTree;
import com.zc.kindergarten.usercenter.vo.GroupUsers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceAuthorityMapper resourceAuthorityMapper;
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Group> selectListByNameAndGroupType(String name, String groupType) {
		Example example = new Example(Group.class);
		if (StringUtils.isNotBlank(name)) {
			example.createCriteria().andLike("name", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(groupType)) {
			example.createCriteria().andEqualTo("groupType", groupType);
		}
		List<Group> groups = groupMapper.selectByExample(example);
		return groups;
	}

	@Override
	public void modifyGroupUsers(int groupId, String members, String leaders) {
		groupMapper.deleteGroupLeadersById(groupId);
		groupMapper.deleteGroupMembersById(groupId);
		if (!org.springframework.util.StringUtils.isEmpty(members)) {
			String[] mem = members.split(",");
			for (String m : mem) {
				groupMapper.insertGroupMembersById(groupId, Integer.parseInt(m));
			}
		}
		if (!org.springframework.util.StringUtils.isEmpty(leaders)) {
			String[] mem = leaders.split(",");
			for (String m : mem) {
				groupMapper.insertGroupLeadersById(groupId, Integer.parseInt(m));
			}
		}
	}

	@Override
	public GroupUsers getGroupUsers(int groupId) {
		return new GroupUsers(userMapper.selectMemberByGroupId(groupId), userMapper.selectLeaderByGroupId(groupId));
	}

	@Override
	public void modifyAuthorityMenu(int groupId, String[] menus) {
		resourceAuthorityMapper.deleteByAuthorityIdAndResourceType(groupId + "", AdminCommonConstant.RESOURCE_TYPE_MENU);
		List<Menu> menuList = menuMapper.selectAll();
		Map<String, String> map = new HashMap(16);
		for (Menu menu : menuList) {
			map.put(menu.getId().toString(), menu.getParentId().toString());
		}
		Set<String> relationMenus = new HashSet<>();
		relationMenus.addAll(Arrays.asList(menus));
		ResourceAuthority authority = null;
		for (String menuId : menus) {
			findParentID(map, relationMenus, menuId);
		}
		for (String menuId : relationMenus) {
			authority = new ResourceAuthority(AdminCommonConstant.AUTHORITY_TYPE_GROUP, AdminCommonConstant.RESOURCE_TYPE_MENU);
			authority.setAuthorityId(groupId + "");
			authority.setResourceId(menuId);
			authority.setParentId("-1");
			resourceAuthorityMapper.insertSelective(authority);
		}
	}

	private void findParentID(Map<String, String> map, Set<String> relationMenus, String id) {
		String parentId = map.get(id);
		if (String.valueOf(AdminCommonConstant.ROOT).equals(id)) {
			return;
		}
		relationMenus.add(parentId);
		findParentID(map, relationMenus, parentId);
	}

	@Override
	public List<AuthorityMenuTree> getAuthorityMenu(int groupId) {
		List<Menu> menus = menuMapper.selectMenuByAuthorityId(String.valueOf(groupId), AdminCommonConstant.AUTHORITY_TYPE_GROUP);
		List<AuthorityMenuTree> trees = new ArrayList<>();
		AuthorityMenuTree node = null;
		for (Menu menu : menus) {
			node = new AuthorityMenuTree();
			node.setText(menu.getTitle());
			BeanUtils.copyProperties(menu, node);
			trees.add(node);
		}
		return trees;
	}

	@Override
	public void modifyAuthorityElement(int groupId, int menuId, int elementId) {
		ResourceAuthority authority = new ResourceAuthority(AdminCommonConstant.AUTHORITY_TYPE_GROUP, AdminCommonConstant.RESOURCE_TYPE_BTN);
		authority.setAuthorityId(groupId + "");
		authority.setResourceId(elementId + "");
		authority.setParentId("-1");
		resourceAuthorityMapper.insertSelective(authority);
	}

	@Override
	public void removeAuthorityElement(int groupId, int menuId, int elementId) {
		ResourceAuthority authority = new ResourceAuthority();
		authority.setAuthorityId(groupId + "");
		authority.setResourceId(elementId + "");
		authority.setParentId("-1");
		resourceAuthorityMapper.delete(authority);
	}

	@Override
    public List<Integer> getAuthorityElement(int groupId) {
		ResourceAuthority authority = new ResourceAuthority(AdminCommonConstant.AUTHORITY_TYPE_GROUP, AdminCommonConstant.RESOURCE_TYPE_BTN);
		authority.setAuthorityId(groupId + "");
		List<ResourceAuthority> authorities = resourceAuthorityMapper.select(authority);
		List<Integer> ids = new ArrayList();
		for (ResourceAuthority auth : authorities) {
			ids.add(Integer.parseInt(auth.getResourceId()));
		}
		return ids;
	}

	@Override
	public List<GroupTree> selectByNameAndGroupType(String name, String groupType) {
		Example example = new Example(Group.class);
		if (StringUtils.isNotBlank(name)) {
			example.createCriteria().andLike("name", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(groupType)) {
			example.createCriteria().andEqualTo("groupType", groupType);
		}
		List<Group> groups = groupMapper.selectByExample(example);
		List<GroupTree> tree = getTree(groups, AdminCommonConstant.ROOT);
		return tree;
	}

	private List<GroupTree> getTree(List<Group> groups, Long root) {
		List<GroupTree> trees = new ArrayList();
		GroupTree node = null;
		for (Group group : groups) {
			node = new GroupTree();
			node.setLabel(group.getName());
			BeanUtils.copyProperties(group, node);
			trees.add(node);
		}
		return TreeUtil.bulid(trees, root);
	}
}
