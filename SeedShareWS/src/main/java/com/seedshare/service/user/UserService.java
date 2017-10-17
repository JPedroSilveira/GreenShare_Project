package com.seedshare.service.user;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.User;

/**
 * Service interface of {@link com.seedshare.entity.User}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface UserService {

	ResponseEntity<?> create(User user);

	ResponseEntity<?> changePassword(User user);

	ResponseEntity<?> changeName(String name);

	ResponseEntity<?> findOne(Long id);

}
