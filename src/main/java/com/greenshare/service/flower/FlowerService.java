package com.greenshare.service.flower;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Flower;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Flower}
 * 
 * @author joao.silva
 */
public interface FlowerService extends BasicService<Flower, Long> {

	ResponseEntity<?> findAll();
	
	ResponseEntity<?> findOneBySpecies(Long id);
	
}
