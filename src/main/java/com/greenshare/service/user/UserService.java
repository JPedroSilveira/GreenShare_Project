package com.greenshare.service.user;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.user.User;

/**
 * Service interface of {@link com.greenshare.entity.user.User}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface UserService {

	ResponseEntity<?> create(User user);

	ResponseEntity<?> changePassword(User user);

	ResponseEntity<?> changeName(String name);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findOneByEmail(String email);

	User findOneUserByEmail(String email);

	ResponseEntity<?> updateUserLoggedIn(User user);

	ResponseEntity<?> deleteByID(Long id);

	ResponseEntity<?> deleteByCurrentUser();

	ResponseEntity<?> addCPF(String cpf);

}
