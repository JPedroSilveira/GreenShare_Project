package com.seedshare.controller.user;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.user.User;

/**
 * Controller interface of {@link com.seedshare.entity.user.User}
 * 
 * @author joao.silva
 */
public interface UserController{
	
	ResponseEntity<?> changePassword(User user);

	ResponseEntity<?> create(User user);
	
	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> changeName(String name);
	
}
