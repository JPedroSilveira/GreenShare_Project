package com.greenshare.controller.city;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.address.City;

/**
 * Controller Interface of {@link com.greenshare.entity.address.City}
 * 
 * @author joao.silva
 */
public interface CityController extends BasicController<City, Long> {

	ResponseEntity<?> findByCountry(Long id);

	ResponseEntity<?> findByState(Long id);
	
	ResponseEntity<?> update(City city);
	
}
