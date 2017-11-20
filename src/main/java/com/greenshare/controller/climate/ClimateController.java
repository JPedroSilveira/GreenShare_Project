package com.greenshare.controller.climate;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.vegetable.Climate;

/**
 * Controller Interface of {@link com.greenshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 */
public interface ClimateController extends BasicController<Climate, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Climate climate);
	
}
