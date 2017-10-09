package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;

/**
 * Repository Interface for Address
 * 
 * @author joao.silva
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
	Iterable<Address> findAllByUser(User user);
}
