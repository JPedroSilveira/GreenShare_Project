package com.seedshare.controller.climate;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Climate;

/**
 * Controller Interface for City
 * 
 * @author joao.silva
 */
public interface ClimateController extends BasicController<Climate, Long> {
	ResponseEntity<?> findAll();
}
