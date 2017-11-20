package com.greenshare.service.fruit;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Fruit;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 */
public interface FruitService extends BasicService<Fruit, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findOneBySpecies(Long id);
	
}
