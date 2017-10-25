package com.seedshare.controller.fruit;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Fruit;

/**
 * Controller interface of {@link com.seedshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface FruitController extends BasicController<Fruit, Long> {

	ResponseEntity<?> findAll();

	ResponseEntity<?> findOneBySpecies(Long id);

}
