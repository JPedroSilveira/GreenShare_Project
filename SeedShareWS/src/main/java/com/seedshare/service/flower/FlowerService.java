package com.seedshare.service.flower;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Flower;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Flower}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface FlowerService extends BasicService<Flower, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findOneBySpecies(Long id);
	
}
