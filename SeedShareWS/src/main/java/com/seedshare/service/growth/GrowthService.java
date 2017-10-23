package com.seedshare.service.growth;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Growth;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Growth}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface GrowthService extends BasicService<Growth, Long>{

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Growth growth);

}
