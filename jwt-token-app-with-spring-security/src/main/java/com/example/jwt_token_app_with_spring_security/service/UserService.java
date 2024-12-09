package com.example.jwt_token_app_with_spring_security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jwt_token_app_with_spring_security.entity.MyUser;

public interface UserService {
	
	public MyUser createUser(MyUser myUser);
	public List<MyUser> getAllUsers();

}