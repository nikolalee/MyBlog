package com.myblog.service;

import com.myblog.entity.User;

public interface UserService {
	User getUserByUsernameAndPassword(String username,String password);
}
