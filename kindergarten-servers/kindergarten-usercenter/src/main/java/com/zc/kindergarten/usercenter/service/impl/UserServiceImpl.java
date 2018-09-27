package com.zc.kindergarten.usercenter.service.impl;

import com.zc.kindergarten.usercenter.entity.User;
import com.zc.kindergarten.usercenter.mapper.UserMapper;
import com.zc.kindergarten.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByUsername(String currentUserName) {
		User user = new User();
		user.setUsername(currentUserName);
		return userMapper.selectOne(user);
	}
}
