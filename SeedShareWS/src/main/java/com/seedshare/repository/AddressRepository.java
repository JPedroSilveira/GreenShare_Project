package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;

/**
 * Repository Interface of {@link com.seedshare.entity.Address}
 * 
 * @author joao.silva
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
	Iterable<Address> findAllByUser(User user);
}
