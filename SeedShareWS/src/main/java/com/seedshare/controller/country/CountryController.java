package com.seedshare.controller.country;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.address.Country;

/**
 * Controller interface of {@link com.seedshare.entity.address.Country}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface CountryController extends BasicController<Country, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Country country);
	
}
