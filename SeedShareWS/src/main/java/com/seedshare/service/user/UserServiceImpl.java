package com.seedshare.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of User Service interface
 * @author joao.silva
 */
@Service
public class UserServiceImpl extends IsHelper implements UserService{
	
	@Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
    	if(isNotNull(user)) {
    		User newUser = new User(user.getCpf(), user.getName(), user.getEmail(), user.getPassword(), user.getIsLegalPerson());
        	if(newUser.isValid() && validUniqueKeys(newUser)){
                User response = userRepository.save(newUser);
                response.cleanPrivateDate();
                return response;
        	}else{
        		if(!isUniqueEmail(newUser.getEmail())) {
        			newUser.addValidationError("Email já cadastrado");
        		}
        		if(!isUniqueCPF(newUser.getCpf())) {
        			newUser.addValidationError("CPF já cadastrado");
        		}
        	}
        	return newUser;
    	}
    	return null;
    }
    
    @Override
    public boolean changePassword(User user) {
    	User currentUser = getCurrentUser();
    	if(user.hasValidPassword()) {
    		currentUser.setPassword(user.getPassword());
    		currentUser.encodePassword();
    		currentUser = userRepository.save(currentUser);
    		if(currentUser != null) {
    			return true;
    		}
    	}
		return false;
    }
    
    @Override 
    public String changeName(String name) {
    	User currentUser = getCurrentUser();
    	currentUser.setName(name);
    	if(currentUser.hasValidName()) {
    		currentUser = userRepository.save(currentUser);
    		if(currentUser != null) {
    			return currentUser.getName();
    		}
    	}
    	return null;
    }
    
    @Override 
	public User findOne(Long id) {
		if(isNotNull(id)) {
			User userDB = userRepository.findOne(id);
			if(isNotNull(userDB)) {
				userDB.cleanPrivateDate();
				return userDB;
			}
		}
		return null;
	}
	
	public User findOneByEmail(String email) {
		if(isNotNull(email)) {
			User userDB =  userRepository.findOneByEmail(email);
			if(isNotNull(userDB)) {
				userDB.cleanPrivateDate();
				return userDB;
			}
		}
		return null;
	}
	
	private User findOneByCpf(String cpf) {
		return userRepository.findOneByCpf(cpf);
	}
	
	private Boolean validUniqueKeys(User user) {
		return this.isUniqueEmail(user.getEmail()) && this.isUniqueCPF(user.getCpf());
	}
	
	private Boolean isUniqueEmail(String email){
		if(isNotNull(email)) {
	    	return this.findOneByEmail(email) == null;
		}
		return true;
	}
	
	private Boolean isUniqueCPF(String cpf){
		if(isNotNull(cpf)) {
	    	return this.findOneByCpf(cpf) == null;
		}
		return true;
	}
	
}
