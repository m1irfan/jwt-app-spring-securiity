package com.example.jwt_token_app_with_spring_security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsernameNotFoundException extends Exception {
	
	public String message;

}
