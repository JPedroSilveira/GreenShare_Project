package com.greenshare.service.state;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.address.State;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.address.State}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface StateService extends BasicService<State, Long> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCountry(Long id);
	
}
