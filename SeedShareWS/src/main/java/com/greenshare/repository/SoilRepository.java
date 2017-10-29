package com.greenshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Soil;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Soil}
 * 
 * @author joao.silva
 */
@Repository
public interface SoilRepository extends CrudRepository<Soil, Long> {

	List<Soil> findAllById(List<Long> id);
	
}
