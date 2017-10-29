package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.user.User;

/**
 * Repository Interface of {@link com.greenshare.entity.user.User}
 * 
 * @author joao.silva
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOneByEmail(String email);

	User findOneByCpf(String cpf);
	
}
