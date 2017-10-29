package com.greenshare.controller.state;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.address.State;

/**
 * Controller interface of {@link com.greenshare.entity.address.State}
 * 
 * @author joao.silva
 */
public interface StateController extends BasicController<State, Long>{

	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCountry(Long id);
	
}
