package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.address.Country;

/**
 * Repository Interface of {@link com.seedshare.entity.address.Country}
 * 
 * @author joao.silva
 */
public interface CountryRepository extends CrudRepository<Country, Long> {
	
	Country findOneByName(String name);
	
}
