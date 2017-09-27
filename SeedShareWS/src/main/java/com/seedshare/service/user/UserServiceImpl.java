package com.seedshare.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.User;
import com.seedshare.repository.UserRepository;
import com.seedshare.security.UserSecurityConfig;

/**
 * Implementation of User Service interface
 * @author joao.silva
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    UserRepository userRepository;
    
    @Autowired
    UserServiceImpl userService;
    
    @Autowired
    private UserSecurityConfig userSecurityConfig;
    
    @Override
    public User create(User user) {
    	User userDB = userService.findOneByEmail(user.getEmail());
    	if(userDB == null) {
    		User newUser =  new User(user.getCpf(), user.getEmail(), user.getPassword(), user.getPhotoId(), user.isLegalPerson());
    		newUser.setPassword(userSecurityConfig.passwordEncoder().encode(newUser.getPassword()));
        	return userRepository.save(newUser);
    	}
    	return null;
    }
    
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findOneByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

}
