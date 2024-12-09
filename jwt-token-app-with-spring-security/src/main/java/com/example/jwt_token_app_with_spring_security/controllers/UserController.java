package com.example.jwt_token_app_with_spring_security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt_token_app_with_spring_security.entity.MyUser;
import com.example.jwt_token_app_with_spring_security.exception.NoRecordFoundException;
import com.example.jwt_token_app_with_spring_security.service.UserDetailsServiceImpl;
import com.example.jwt_token_app_with_spring_security.service.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {

	
	@Autowired
	//private UserDetailsServiceImpl userService;
	private UserServiceImpl userService;
	
	@GetMapping("all")
	public ResponseEntity<?> getAllUser() throws NoRecordFoundException{
		List<MyUser> myUsers = userService.getAllUsers();
		if(myUsers!=null && !myUsers.isEmpty()) {
			return new ResponseEntity<List<MyUser>>(myUsers, HttpStatus.OK);
		}
		throw new NoRecordFoundException("No Record Found!");
	}
	
	@PostMapping("create")
	public ResponseEntity<?> createUser(@RequestBody MyUser myUser){
		
		if(myUser!=null) {
			MyUser user2 = userService.createUser(myUser);
			return new ResponseEntity<MyUser>(user2, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Bad Request!", HttpStatus.BAD_REQUEST);
	}
	
}
