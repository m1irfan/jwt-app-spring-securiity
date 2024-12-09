package com.example.jwt_token_app_with_spring_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt_token_app_with_spring_security.dto.LoginDTO;
import com.example.jwt_token_app_with_spring_security.exception.UsernameNotFoundException;
import com.example.jwt_token_app_with_spring_security.jwt.JwtService;

@RestController
@RequestMapping("user")
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired 
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws UsernameNotFoundException{

		if(loginDTO!=null) {

			Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

			if(authentication.isAuthenticated()) {
				String token = jwtService.generateToken(userDetailsService.loadUserByUsername(loginDTO.getUsername()));
				return new ResponseEntity<String>(token, HttpStatus.OK);
			}

		}
		throw new UsernameNotFoundException("Invalid Login Credentials!");

	}
}
