package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Color;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Color}
 * 
 * @author joao.silva
 */
@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
	
	Color findOneByName(String name);
	
}
