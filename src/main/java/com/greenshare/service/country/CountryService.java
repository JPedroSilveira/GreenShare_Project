package com.greenshare.service.country;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.address.Country;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.address.Country}
 * 
 * @author joao.silva
 */
public interface CountryService extends BasicService<Country, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Country country);
	
}
