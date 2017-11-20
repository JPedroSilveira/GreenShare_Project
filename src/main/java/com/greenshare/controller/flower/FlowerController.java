package com.greenshare.controller.flower;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.vegetable.Flower;

/**
 * Controller interface of {@link com.greenshare.entity.vegetable.Flower}
 * 
 * @author joao.silva
 */
public interface FlowerController extends BasicController<Flower, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findOneBySpecies(Long id);
	
}
