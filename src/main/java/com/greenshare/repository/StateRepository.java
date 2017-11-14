package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.address.State;

/**
 * Repository Interface of {@link com.greenshare.entity.address.State}
 * 
 * @author joao.silva
 */
@Repository
public interface StateRepository extends CrudRepository<State, Long> {

	State findOneByCountryAndNameIgnoreCase(Long id, String name);

	Iterable<State> findAllByCountry(Long id);

}
