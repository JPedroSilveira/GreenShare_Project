package com.seedshare.service.user;

import com.seedshare.entity.User;
import com.seedshare.service.BasicService;

/**
 * Service interface of User
 * @author joao.silva
 */
public interface UserService extends BasicService<User,Long>{
	
	User findOneByEmail(String email);
	
	User create(User user);
	
	User findOneByCpf(String cpf);
}
