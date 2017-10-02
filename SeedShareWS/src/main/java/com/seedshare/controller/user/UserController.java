package com.seedshare.controller.user;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.User;

/**
 * @author joao.silva
 */
public interface UserController{
	ResponseEntity<String> changePassword(User user);
	
	ResponseEntity<?> changeName(String name);
}
