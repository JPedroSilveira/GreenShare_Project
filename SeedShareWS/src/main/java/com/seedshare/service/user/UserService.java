package com.seedshare.service.user;

import com.seedshare.entity.User;

/**
 * Service interface of User
 * @author joao.silva
 */
public interface UserService{
		
	User create(User user);

	boolean changePassword(User user);

	String changeName(String name);

	User findOne(Long id);

}
