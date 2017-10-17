package com.seedshare.controller.user;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.User;

/**
 * Controller interface of {@link com.seedshare.entity.User}
 * 
 * @author joao.silva
 */
public interface UserController{
	ResponseEntity<?> changePassword(User user);

	ResponseEntity<?> changeName(User user);

	ResponseEntity<?> create(User user);
}
