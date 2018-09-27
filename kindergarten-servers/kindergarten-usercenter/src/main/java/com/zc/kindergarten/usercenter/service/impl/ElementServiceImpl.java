package com.zc.kindergarten.usercenter.service.impl;

import com.zc.kindergarten.usercenter.entity.Element;
import com.zc.kindergarten.usercenter.mapper.ElementMapper;
import com.zc.kindergarten.usercenter.service.ElementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {

	@Autowired
	private ElementMapper elementMapper;

	@Override
	public List<Element> selectByNameAndMenuId(String name, int menuId) {
		Example example = new Example(Element.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menuId", menuId);
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		List<Element> elements = elementMapper.selectByExample(example);
		return elements;
	}

	@Override
	public List<Element> getAuthorityElementByUserId(String userId, String menuId) {

		List<Element> elements = elementMapper.selectAuthorityMenuElementByUserId(userId, menuId);
		return elements;
	}

	@Override
	public List<Element> getAuthorityElementByUserId(String userId) {
		List<Element> elements = elementMapper.selectAuthorityElementByUserId(userId);
		return elements;
	}
}
