package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Country;

/**
 * Repository Interface of {@link com.seedshare.entity.Country}
 * 
 * @author joao.silva
 */
public interface CountryRepository extends CrudRepository<Country, Long> {
	Country findOneByName(String name);
}
