package com.seedshare.service.state;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.address.Country;
import com.seedshare.entity.address.State;
import com.seedshare.service.BasicService;

/**
 * Service interface of Climate
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface StateService extends BasicService<State, Long> {
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCountry(Country country);
}
