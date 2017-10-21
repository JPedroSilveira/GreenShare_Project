package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Country;
import com.seedshare.entity.State;

/**
 * Repository Interface of {@link com.seedshare.entity.State}
 * 
 * @author joao.silva
 */
public interface StateRepository extends CrudRepository<State, Long> {

	State findOneByCountryAndNameContainsIgnoreCase(Country country, String name);

	Iterable<State> findAllByCountry(Country country);

}
