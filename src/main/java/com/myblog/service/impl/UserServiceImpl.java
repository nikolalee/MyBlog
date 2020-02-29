package com.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entity.User;
import com.myblog.repository.UserRepository;
import com.myblog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository user;
	
	@Override
	public User getUserByUsernameAndPassword(String username,String password) {
		return user.findByUsernameAndPassword(username,password);
	}
	
}
