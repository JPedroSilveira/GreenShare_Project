package com.seedshare.controller.climate;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Climate;

/**
 * Controller Interface of {@link com.seedshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface ClimateController extends BasicController<Climate, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Climate climate);
	
}
