package com.greenshare.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.greenshare.entity.user.User;
import com.greenshare.service.user.UserServiceImpl;

public abstract class UserUtils {
	
	@Autowired
	UserServiceImpl userService;
	
	protected User getCurrentUser() {
    	Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String email;
    	if (currentUser instanceof UserDetails ) {
    		email = ( (UserDetails)currentUser).getUsername();
    	} else {
    		email = currentUser.toString();
    	}
    	return userService.findOneUserByEmail(email);
    }
	
	protected Long getCurrentUserId() {
		return getCurrentUser().getId();
	}
}
