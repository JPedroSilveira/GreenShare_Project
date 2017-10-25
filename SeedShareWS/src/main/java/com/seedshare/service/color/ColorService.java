package com.seedshare.service.color;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Color;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Color}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface ColorService extends BasicService<Color, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Color color);
	
}
