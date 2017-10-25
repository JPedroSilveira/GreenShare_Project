package com.seedshare.controller.soil;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Soil;

/**
 * Controller interface of {@link com.seedshare.entity.vegetable.Soil}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface SoilController extends BasicController<Soil, Long>{

	ResponseEntity<?> findAll();
	
}
