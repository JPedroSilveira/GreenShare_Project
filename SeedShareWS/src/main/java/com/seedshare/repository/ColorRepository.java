package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.vegetable.Color;

/**
 * Repository Interface of {@link com.seedshare.entity.vegetable.Color}
 * 
 * @author joao.silva
 */
public interface ColorRepository extends CrudRepository<Color, Long> {
	
	Color findOneByName(String name);
	
}
