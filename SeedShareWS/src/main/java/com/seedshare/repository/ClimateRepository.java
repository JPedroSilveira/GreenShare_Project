package com.seedshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.vegetable.Climate;

/**
 * Repository Interface of {@link com.seedshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 */
public interface ClimateRepository extends CrudRepository<Climate, Long> {

	List<Climate> findAllById(List<Long> idList);

}
