package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.vegetable.Climate;

/**
 * Repository Interface of {@link com.seedshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 */
public interface ClimateRepository extends CrudRepository<Climate, Long> {

}
