package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.address.State;

/**
 * Repository Interface of {@link com.seedshare.entity.address.State}
 * 
 * @author joao.silva
 */
public interface StateRepository extends CrudRepository<State, Long> {

	State findOneByCountryAndNameIgnoreCase(Long id, String name);

	Iterable<State> findAllByCountry(Long id);

}
