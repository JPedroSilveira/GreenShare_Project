package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Color;

/**
 * Repository Interface of {@link com.seedshare.entity.Color}
 * 
 * @author joao.silva
 */
public interface ColorRepository extends CrudRepository<Color, Long> {
	Color findOneByName(String name);
}
