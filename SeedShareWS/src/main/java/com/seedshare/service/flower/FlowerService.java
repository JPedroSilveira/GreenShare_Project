package com.seedshare.service.flower;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Flower;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Flower}
 * 
 * @author joao.silva
 */
public interface FlowerService extends BasicService<Flower, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findAllBySpecies(Long id);
	
}
