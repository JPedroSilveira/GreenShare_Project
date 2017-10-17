package com.seedshare.controller.auth;

import org.springframework.http.ResponseEntity;

/**
 * Controller interface of Login 
 * 
 * @author joao.silva
 */
public interface LoginController {

	ResponseEntity<?> getUserDetails();
	
}
