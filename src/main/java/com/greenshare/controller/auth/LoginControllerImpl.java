package com.greenshare.controller.auth;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenshare.service.user.UserServiceImpl;

/**
 * Controller class for login of {@link com.greenshare.entity.user.User}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@RestController
@RequestMapping("/user/auth")
public class LoginControllerImpl implements LoginController{

    @Autowired
    UserServiceImpl userService;
    
    @Override
    @GetMapping("login")
    public ResponseEntity<?> getUserDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername)
                .map(userService::findOneByEmail)
                .orElse(null);
    }
}       