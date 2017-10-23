package com.seedshare.service.climate;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Climate;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 */
public interface ClimateService extends BasicService<Climate, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Climate climate);
	
}
