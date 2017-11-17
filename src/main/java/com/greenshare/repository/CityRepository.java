package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.address.City;

/**
 * Repository Interface of {@link com.greenshare.entity.address.City}
 * 
 * @author joao.silva
 */
@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	Iterable<City> findAllByStateId(Long id);

	Iterable<City> findAllByStateCountryId(Long id);
	
	City findOneByStateIdAndNameIgnoreCase(Long id, String name);
	
}
