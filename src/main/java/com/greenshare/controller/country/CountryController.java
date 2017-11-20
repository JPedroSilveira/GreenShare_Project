package com.greenshare.controller.country;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.address.Country;

/**
 * Controller interface of {@link com.greenshare.entity.address.Country}
 * 
 * @author joao.silva
 */
public interface CountryController extends BasicController<Country, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Country country);
	
}
