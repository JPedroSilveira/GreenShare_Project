package com.seedshare.controller.auth;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.User;

/**
 * @author joao.silva
 */
public interface LoginController {

	User getUserDetails();
	
	ResponseEntity<User> create(User user);
}
