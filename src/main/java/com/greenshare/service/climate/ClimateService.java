package com.greenshare.service.climate;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Climate;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Climate}
 * 
 * @author joao.silva
 */
public interface ClimateService extends BasicService<Climate, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Climate climate);
	
}
