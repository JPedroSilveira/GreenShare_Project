package com.seedshare.controller.auth;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.service.user.UserServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/auth")
public class LoginControllerImpl implements LoginController{

    @Autowired
    UserServiceImpl userService;
    
    @Override
    @GetMapping("/login")
    public com.seedshare.entity.User getUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername)
                .map(userService::findOneByEmail)
                .orElse(null);
    }

    @Override
    @PostMapping(value = "/register")
    public ResponseEntity<?> create(@RequestBody @Valid com.seedshare.entity.User user) {
    	com.seedshare.entity.User createdUser = userService.create(user); 
        if(createdUser.getValidationErrors().isEmpty()){
            return new ResponseEntity<com.seedshare.entity.User>(createdUser, HttpStatus.CREATED);
        }else{
        	return new ResponseEntity<List<String>>(createdUser.getValidationErrors(), HttpStatus.BAD_REQUEST);        	
        }
    }  
}       