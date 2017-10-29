package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.vegetable.Color;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Color}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface ColorRepository extends CrudRepository<Color, Long> {
	
	Color findOneByName(String name);
	
}
