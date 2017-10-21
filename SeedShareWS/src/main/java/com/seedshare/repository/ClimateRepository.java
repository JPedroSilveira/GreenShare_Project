package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Climate;

/**
 * Repository Interface of {@link com.seedshare.entity.Climate}
 * 
 * @author joao.silva
 */
public interface ClimateRepository extends CrudRepository<Climate, Long> {

}
