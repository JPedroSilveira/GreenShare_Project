package com.seedshare.controller.city;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.address.City;

/**
 * Controller Interface of {@link com.seedshare.entity.address.City}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface CityController extends BasicController<City, Long> {

	ResponseEntity<?> findByCountry(Long id);

	ResponseEntity<?> findByState(Long id);
	
	ResponseEntity<?> update(City city);
	
}
