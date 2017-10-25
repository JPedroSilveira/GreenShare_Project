package com.seedshare.service.fruit;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Fruit;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Fruit}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface FruitService extends BasicService<Fruit, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findOneBySpecies(Long id);
	
}
