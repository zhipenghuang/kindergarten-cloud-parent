package com.zc.kindergarten.usercenter.service;

import com.zc.kindergarten.usercenter.entity.Element;

import java.util.List;

public interface ElementService {
	List<Element> selectByNameAndMenuId(String name, int menuId);

	List<Element> getAuthorityElementByUserId(String userId, String menuId);

	List<Element> getAuthorityElementByUserId(String userId);
}
