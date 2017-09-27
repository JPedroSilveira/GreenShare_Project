package com.seedshare.controller.user;

import com.seedshare.entity.User;

/**
 * @author joao.silva
 */
public interface UserController{
	User changePassword(User user);
	
	User getPhoto(User user);
}
