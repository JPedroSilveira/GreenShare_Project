package com.seedshare.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.UserUtils;
import com.seedshare.entity.User;
import com.seedshare.service.user.UserServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl extends UserUtils implements UserController{
	@Autowired
	UserServiceImpl userService;
	
	@Override
	@PutMapping(value = "/change/password/")
    public ResponseEntity<String> changePassword(@RequestBody User user) {
		User currentUser = getCurrentUser();
		currentUser.setPassword(user.getPassword());
		currentUser.encodePassword();
		User newUser = userService.save(currentUser);
		if(newUser != null) {
			return new ResponseEntity<String>("Senha alterada com sucesso", HttpStatus.OK);   
		}else {
			return new ResponseEntity<String>("Falha ao alterar a senha", HttpStatus.INTERNAL_SERVER_ERROR);   
		}
    }
	
	@Override
	@PutMapping("/change/name/{name}")
	public ResponseEntity<?> changeName(@PathVariable String name) {
		User currentUser = getCurrentUser();
		currentUser.setName(name);
		currentUser.generateNewValidation();
		if(currentUser.isValid()) {  
			if(userService.save(currentUser) != null) {
				return new ResponseEntity<String>("Senha alterada com sucesso", HttpStatus.OK);   
			}else {
				return new ResponseEntity<String>("Falha ao alterar a senha", HttpStatus.INTERNAL_SERVER_ERROR);   
			}
		}
		return new ResponseEntity<List<String>>(currentUser.getValidationErrors(), HttpStatus.BAD_REQUEST);   
    }
}
