package com.zc.kindergarten.usercenter.controller;

import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.usercenter.entity.Group;
import com.zc.kindergarten.usercenter.service.GroupService;
import com.zc.kindergarten.usercenter.vo.AuthorityMenuTree;
import com.zc.kindergarten.usercenter.vo.GroupTree;
import com.zc.kindergarten.usercenter.vo.GroupUsers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2017-06-12 8:49
 */
@Controller
@RequestMapping("group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Group>> list(String name, String groupType) {
		if (StringUtils.isBlank(name) && StringUtils.isBlank(groupType)) {
			return new ResponseEntity(new ArrayList<Group>());
		}
		List<Group> groups = groupService.selectListByNameAndGroupType(name, groupType);
		return new ResponseEntity<>(groups);
	}


	@RequestMapping(value = "/{id}/user", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity modifiyUsers(@PathVariable int id, String members, String leaders) {
		groupService.modifyGroupUsers(id, members, leaders);
		return new ResponseEntity();
	}

	@RequestMapping(value = "/{id}/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<GroupUsers> getUsers(@PathVariable int id) {

		return new ResponseEntity(groupService.getGroupUsers(id));
	}

	@RequestMapping(value = "/{id}/authority/menu", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modifyMenuAuthority(@PathVariable int id, String menuTrees) {
		String[] menus = menuTrees.split(",");
		groupService.modifyAuthorityMenu(id, menus);
		return new ResponseEntity();
	}

	@RequestMapping(value = "/{id}/authority/menu", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AuthorityMenuTree>> getMenuAuthority(@PathVariable int id) {
		return new ResponseEntity(groupService.getAuthorityMenu(id));
	}

	@RequestMapping(value = "/{id}/authority/element/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addElementAuthority(@PathVariable int id, int menuId, int elementId) {
		groupService.modifyAuthorityElement(id, menuId, elementId);
		return new ResponseEntity();
	}

	@RequestMapping(value = "/{id}/authority/element/remove", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeElementAuthority(@PathVariable int id, int menuId, int elementId) {
		groupService.removeAuthorityElement(id, menuId, elementId);
		return new ResponseEntity();
	}

	@RequestMapping(value = "/{id}/authority/element", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Integer>> getElementAuthority(@PathVariable int id) {
		return new ResponseEntity(groupService.getAuthorityElement(id));
	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<GroupTree>> tree(String name, String groupType) {
		if (StringUtils.isBlank(name) && StringUtils.isBlank(groupType)) {
			return new ResponseEntity(new ArrayList<GroupTree>());
		}
		List<GroupTree> groups = groupService.selectByNameAndGroupType(name, groupType);
		return new ResponseEntity(groups);
	}

}
