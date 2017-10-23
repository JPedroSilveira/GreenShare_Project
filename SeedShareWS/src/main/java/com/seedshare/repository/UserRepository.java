package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.user.User;

/**
 * Repository Interface of {@link com.seedshare.entity.user.User}
 * 
 * @author joao.silva
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOneByEmail(String email);

	User findOneByCpf(String cpf);
	
}
