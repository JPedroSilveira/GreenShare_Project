package com.greenshare.service.city;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.address.City;
import com.greenshare.service.BasicService;

/**
 * Service Interface of {@link com.greenshare.entity.address.City}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface CityService extends BasicService<City, Long> {
	
	ResponseEntity<?> findByState(Long id);

	ResponseEntity<?> findByCountry(Long id);
	
	ResponseEntity<?> update(City city);
	
}
