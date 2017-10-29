package com.greenshare.service.growth;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Growth;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Growth}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface GrowthService extends BasicService<Growth, Long>{

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Growth growth);

}
