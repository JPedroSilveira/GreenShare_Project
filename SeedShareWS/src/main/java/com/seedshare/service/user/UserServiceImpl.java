package com.seedshare.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.User;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of User Service interface
 * @author joao.silva
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
    	User newUser = new User(user.getCpf(), user.getName(), user.getEmail(), user.getPassword(), user.getIsLegalPerson());
    	if(newUser.isValid() && validUniqueKeys(newUser)){
            return userRepository.save(newUser);
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
	
	private User findOneByCpf(String cpf) {
		return userRepository.findOneByCpf(cpf);
	}
	
	private Boolean validUniqueKeys(User user) {
		return this.isUniqueEmail(user.getEmail()) && this.isUniqueCPF(user.getCpf());
	}
	
	private Boolean isUniqueEmail(String email){
    	return this.findOneByEmail(email) == null;
	}
	
	private Boolean isUniqueCPF(String cpf){
    	return this.findOneByCpf(cpf) == null;
	}
	
}
