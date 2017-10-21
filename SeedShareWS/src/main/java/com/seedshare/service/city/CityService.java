package com.seedshare.service.city;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.City;
import com.seedshare.service.BasicService;

/**
 * Service Interface of {@link com.seedshare.entity.City}
 * 
 * @author joao.silva
 */
public interface CityService extends BasicService<City, Long> {
	ResponseEntity<?> findByState(Long id);

	ResponseEntity<?> findByCountry(Long id);
}
