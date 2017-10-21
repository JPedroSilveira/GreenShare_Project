package com.seedshare.service.country;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Country;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Country}
 * 
 * @author joao.silva
 */
public interface CountryService extends BasicService<Country, Long> {
	ResponseEntity<?> findAll();
}
