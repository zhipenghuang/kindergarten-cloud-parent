package com.zc.kindergarten.usercenter.service;

import com.zc.kindergarten.usercenter.entity.User;

public interface UserService {

	User getUserByUsername(String currentUserName);
}
