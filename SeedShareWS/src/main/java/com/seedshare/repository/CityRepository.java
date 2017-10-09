package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.City;
import com.seedshare.entity.Country;
import com.seedshare.entity.State;

/**
 * Repository Interface for City
 * 
 * @author joao.silva
 */
public interface CityRepository extends CrudRepository<City, Long> {
	Iterable<City> findAllByState(State state);

	Iterable<City> findAllByCountry(Country country);
}
