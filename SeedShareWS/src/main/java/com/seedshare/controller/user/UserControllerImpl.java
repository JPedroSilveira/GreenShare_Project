package com.seedshare.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.controller.Controller;
import com.seedshare.entity.User;
import com.seedshare.service.user.UserServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl extends Controller implements UserController{
	@Autowired
	UserServiceImpl userService;
	
	@Override
	@PutMapping(value = "/changePassword")
    public User changePassword(@RequestBody User user) {
		User userDB = userService.findOne(user.getId());
		userDB.setPassword(user.getPassword());
		return userService.save(user);   
    }
	
	@Override
	@GetMapping(value = "/photo/get")
    public User getPhoto(@RequestBody User user) {
		User currentUser = getCurrentUser();
       currentUser.setPassword(user.getPassword());
       return userService.save(user);  
    }
}
