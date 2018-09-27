package com.zc.kindergarten.usercenter.controller;

import com.zc.kindergarten.common.context.BaseContextHandler;
import com.zc.kindergarten.common.msg.ResponseEntity;
import com.zc.kindergarten.usercenter.entity.Element;
import com.zc.kindergarten.usercenter.service.ElementService;
import com.zc.kindergarten.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author hzp
 * @create 2017-06-23 20:30
 */
@Controller
@RequestMapping("element")
public class ElementController {

	@Autowired
	private ElementService elementService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Element>> page(@RequestParam(defaultValue = "10") int limit,
	                                          @RequestParam(defaultValue = "1") int offset, String name, @RequestParam(defaultValue = "0") int menuId) {
		Example example = new Example(Element.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menuId", menuId);
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		List<Element> elements = elementService.selectByNameAndMenuId(name, menuId);
		return new ResponseEntity(elements);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Element>> getAuthorityElement(String menuId) {
		Long userId = userService.getUserByUsername(getCurrentUserName()).getId();
		List<Element> elements = elementService.getAuthorityElementByUserId(userId + "", menuId);
		return new ResponseEntity(elements);
	}

	@RequestMapping(value = "/user/menu", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Element>> getAuthorityElement() {

		Long userId = userService.getUserByUsername(getCurrentUserName()).getId();
		List<Element> elements = elementService.getAuthorityElementByUserId(userId + "");
		return new ResponseEntity(elements);
	}

	public String getCurrentUserName() {
		return BaseContextHandler.getUsername();
	}
}
