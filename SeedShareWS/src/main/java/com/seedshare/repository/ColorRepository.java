package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Color;

/**
 * Repository Interface for Color
 * 
 * @author joao.silva
 */
public interface ColorRepository extends CrudRepository<Color, Long> {
	Color findOneByName(String name);
}
