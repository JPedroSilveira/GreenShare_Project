package com.seedshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;

/**
 * Repository Class for Address
 * @author joao.silva
 */
public interface AddressRepository extends CrudRepository<Address, Long>{
	List<Address> findAllByUser(User user);
}
