package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Country;

/**
 * Repository Interface for Country
 * 
 * @author joao.silva
 */
public interface CountryRepository extends CrudRepository<Country, Long> {
	Country findOneByName(String name);
}
