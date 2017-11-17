package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.address.Address;
import com.greenshare.entity.user.User;

/**
 * Repository Interface of {@link com.greenshare.entity.address.Address}
 * 
 * @author joao.silva
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
	
	Iterable<Address> findAllByUser(User user);
	
}
