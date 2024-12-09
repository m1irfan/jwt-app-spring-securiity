package com.example.jwt_token_app_with_spring_security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt_token_app_with_spring_security.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

	public MyUser findByUsername(String username);
}