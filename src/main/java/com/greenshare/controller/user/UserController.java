package com.greenshare.controller.user;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.user.User;

/**
 * Controller interface of {@link com.greenshare.entity.user.User}
 * 
 * @author joao.silva
 */
public interface UserController{
	
	ResponseEntity<?> changePassword(User user);

	ResponseEntity<?> create(User user);
	
	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> updateWithOutPassword(User user);
	
	ResponseEntity<?> deleteByID(Long id);

	ResponseEntity<?> deleteByCurrentUser();

	ResponseEntity<?> validEmail(Email email);
	
}
