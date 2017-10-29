package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.address.Address;
import com.greenshare.entity.user.User;

/**
 * Repository Interface of {@link com.greenshare.entity.address.Address}
 * 
 * @author joao.silva
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
	
	Iterable<Address> findAllByUser(User user);
	
}
