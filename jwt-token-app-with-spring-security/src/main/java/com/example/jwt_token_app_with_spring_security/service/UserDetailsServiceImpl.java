package com.example.jwt_token_app_with_spring_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt_token_app_with_spring_security.dao.MyUserRepository;
import com.example.jwt_token_app_with_spring_security.entity.MyUser;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		
		MyUser user = myUserRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found!");
		}
		return User.builder()
		.username(username)
		.password(user.getPassword())
		.build();
	}


}