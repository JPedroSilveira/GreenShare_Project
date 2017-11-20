package com.greenshare.controller.fruit;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.vegetable.Fruit;

/**
 * Controller interface of {@link com.greenshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 */
public interface FruitController extends BasicController<Fruit, Long> {

	ResponseEntity<?> findAll();

	ResponseEntity<?> findOneBySpecies(Long id);

}
