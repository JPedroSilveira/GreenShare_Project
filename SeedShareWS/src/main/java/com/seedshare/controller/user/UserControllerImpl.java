package com.seedshare.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.user.UserServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl extends IsHelper implements UserController{
	@Autowired
	UserServiceImpl userService;
	
	@Override
	@PutMapping(value = "/password/")
    public ResponseEntity<String> changePassword(@RequestBody User user) {
		if(userService.changePassword(user)) {
			return new ResponseEntity<String>("Senha alterada com sucesso", HttpStatus.OK);   
		}else {
			return new ResponseEntity<String>("Falha ao alterar a senha", HttpStatus.INTERNAL_SERVER_ERROR);   
		}
    }
	
	@Override
	@PutMapping("/name/")
	public ResponseEntity<?> changeName(@RequestBody User user) {
		String response = userService.changeName(user.getName());
		if(isNotNull(response)){
			return new ResponseEntity<String>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Falha ao atualizar nome", HttpStatus.BAD_REQUEST); 
    }
	
	@Override
	@PutMapping("/name/string/")
	public ResponseEntity<?> changeName(@RequestBody String name) {
		String response = userService.changeName(name);
		if(isNotNull(response)){
			return new ResponseEntity<String>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Falha ao atualizar nome", HttpStatus.BAD_REQUEST); 
    }
}
