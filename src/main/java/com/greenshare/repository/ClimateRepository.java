package com.greenshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Climate;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 */
@Repository
public interface ClimateRepository extends CrudRepository<Climate, Long> {

	List<Climate> findAllById(List<Long> idList);

}
