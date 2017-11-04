package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.address.Country;

/**
 * Repository Interface of {@link com.greenshare.entity.address.Country}
 * 
 * @author joao.silva
 */
public interface CountryRepository extends CrudRepository<Country, Long> {
	
	Country findOneByName(String name);
	
}
