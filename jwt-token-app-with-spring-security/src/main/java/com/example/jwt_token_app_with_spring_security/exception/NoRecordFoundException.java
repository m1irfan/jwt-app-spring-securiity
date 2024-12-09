package com.example.jwt_token_app_with_spring_security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoRecordFoundException extends Exception {
	
	private String message;

}
