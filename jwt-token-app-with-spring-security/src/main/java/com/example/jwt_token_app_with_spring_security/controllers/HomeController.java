package com.example.jwt_token_app_with_spring_security.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

	@GetMapping("user")
	//@PreAuthorize("hasRole('USER')")
	public String userHome() {
		return "Welcome to user dashboard";
	}
	
	@GetMapping("admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminHome() {
		return "Welcome to admin dashboard";
	}
}