package com.example.jwt_token_app_with_spring_security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt_token_app_with_spring_security.dao.MyUserRepository;
import com.example.jwt_token_app_with_spring_security.entity.MyUser;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Override
	public MyUser createUser(MyUser myUser) {
		myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
		return myUserRepository.save(myUser);
		
	}

	@Override
	public List<MyUser> getAllUsers() {
		return myUserRepository.findAll();
	}

}
