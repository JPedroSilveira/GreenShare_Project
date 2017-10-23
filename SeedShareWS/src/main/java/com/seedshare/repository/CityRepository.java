package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.address.City;

/**
 * Repository Interface of {@link com.seedshare.entity.address.City}
 * 
 * @author joao.silva
 */
public interface CityRepository extends CrudRepository<City, Long> {

	Iterable<City> findAllByState(Long id);

	Iterable<City> findAllByStateCountry(Long id);

}
