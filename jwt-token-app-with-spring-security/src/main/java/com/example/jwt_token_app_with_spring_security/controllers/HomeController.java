package com.example.jwt_token_app_with_spring_security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class HomeController {

	@GetMapping("home")
	public String home() {
		return "Welcome to user dashboard";
	}
}
