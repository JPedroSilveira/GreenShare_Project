package com.seedshare.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.user.UserServiceImpl;

/**
 * Controller implementation of {@link com.seedshare.controller.user.UserController}
 *
 * @author joao.silva
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl extends IsHelper implements UserController{
	@Autowired
	UserServiceImpl userService;
	
	@Override
	@PutMapping(value = "/password/")
    public ResponseEntity<?> changePassword(@RequestBody User user) {
		return userService.changePassword(user);
    }
	
	@Override
	@PutMapping("/name/")
	public ResponseEntity<?> changeName(@RequestBody User user) {
		return userService.changeName(user.getName());
    }
	
	@Override
    @PostMapping(value = "/register/")
    public ResponseEntity<?> create(@RequestBody @Valid User user) {
    	return userService.create(user); 
    }
}
