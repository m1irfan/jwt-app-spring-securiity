package com.example.jwt_token_app_with_spring_security.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class MyUser implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String role;
	
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Transient
	Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	
	public MyUser(MyUser byUsername) {
        this.username = byUsername.getUsername();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();
        
        auths.add(new SimpleGrantedAuthority(byUsername.getRole().toUpperCase()));
        this.authorities = auths;
    }


}
