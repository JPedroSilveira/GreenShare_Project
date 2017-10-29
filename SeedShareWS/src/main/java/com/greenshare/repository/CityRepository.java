package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.address.City;

/**
 * Repository Interface of {@link com.greenshare.entity.address.City}
 * 
 * @author joao.silva
 */
public interface CityRepository extends CrudRepository<City, Long> {

	Iterable<City> findAllByState(Long id);

	Iterable<City> findAllByStateCountry(Long id);
	
	City findOneByStateAndNameIgnoreCase(Long id, String name);
	
}
