package com.greenshare.controller.soil;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.vegetable.Soil;

/**
 * Controller interface of {@link com.greenshare.entity.vegetable.Soil}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface SoilController extends BasicController<Soil, Long>{

	ResponseEntity<?> findAll();
	
}
