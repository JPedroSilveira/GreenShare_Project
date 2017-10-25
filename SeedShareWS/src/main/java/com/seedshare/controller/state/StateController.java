package com.seedshare.controller.state;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.address.State;

/**
 * Controller interface of {@link com.seedshare.entity.address.State}
 * 
 * @author joao.silva
 */
public interface StateController extends BasicController<State, Long>{

	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCountry(Long id);
	
}
