package com.seedshare.service.city;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.address.City;
import com.seedshare.service.BasicService;

/**
 * Service Interface of {@link com.seedshare.entity.address.City}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface CityService extends BasicService<City, Long> {
	
	ResponseEntity<?> findByState(Long id);

	ResponseEntity<?> findByCountry(Long id);
	
	ResponseEntity<?> update(City city);
	
}
