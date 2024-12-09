package com.example.jwt_token_app_with_spring_security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private SecretKey secretKey = Jwts.SIG.HS256.key().build();

	
	private long EXPIRATION_TIME = 1000*60*5;
	
	//private SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
	
	public String generateToken(UserDetails userDetails) {
		
		Map<String,String> claims = new HashMap<>();
		claims.put("name", userDetails.getUsername());
		
		return Jwts.builder()
		.claims(claims)
		.subject(userDetails.getUsername())
		.issuedAt(new Date())
		.expiration(new Date( System.currentTimeMillis() + EXPIRATION_TIME))
		.signWith(secretKey)
		.compact();
		
	}
	
	public boolean validateToken(String token, String username) {
		
		return Jwts
				.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject()
				.equals(username);
				
	}

	public String extractUsername(String token) {
		
			return getClaims(token)
			.getSubject();
	}
	
	public Claims getClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean isTokenValid(String jwt) {
		
		Claims claims = getClaims(jwt);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}

}