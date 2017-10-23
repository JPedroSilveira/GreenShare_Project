package com.seedshare.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.seedshare.service.user.UserServiceImpl;

/**
 * Authentication service 
 * @author joao.silva
 */
@Service
public class UserSecurityService implements UserDetailsService {
    
	@Autowired
    UserServiceImpl userService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<GrantedAuthority> grants = new ArrayList<>();
        com.seedshare.entity.user.User user = userService.findOneUserByEmail(email);
        return new User(user.getEmail(),user.getPassword(),grants);
    }
}
