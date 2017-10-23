package com.seedshare.controller.country;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.address.Country;

/**
 * Controller interface for Color
 * 
 * @author joao.silva
 */
public interface CountryController extends BasicController<Country, Long> {
	ResponseEntity<?> findAll();
}
