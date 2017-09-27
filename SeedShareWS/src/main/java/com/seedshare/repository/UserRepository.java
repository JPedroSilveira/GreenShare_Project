package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.User;
/**
 * Classe de repositório para Usuario
 * @author joao.silva
 */
@Repository
public interface  UserRepository extends CrudRepository<User, Long>{
	User findOneByEmail(String email);
}
