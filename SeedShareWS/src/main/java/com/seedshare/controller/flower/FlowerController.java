package com.seedshare.controller.flower;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Flower;

/**
 * Controller interface of {@link com.seedshare.entity.vegetable.Flower}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface FlowerController extends BasicController<Flower, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findOneBySpecies(Long id);
	
}
