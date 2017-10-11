package com.seedshare.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.City;

/**
 * Repository Interface for City
 * 
 * @author joao.silva
 */
public interface CityRepository extends CrudRepository<City, Long> {
	Iterable<City> findAllByState(Long id);

	@Query(value = "select c from City c where c.state.country.id = ?1")
	Iterable<City> findAllByCountry(Long id);
}
