package com.greenshare.controller.auth;

import org.springframework.http.ResponseEntity;

/**
 * Controller interface for login of {@link com.greenshare.entity.user.User} 
 * 
 * @author joao.silva
 */
public interface LoginController {

	ResponseEntity<?> getUserDetails();
	
}
