package com.greenshare.service.color;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Color;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Color}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface ColorService extends BasicService<Color, Long> {
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> update(Color color);
	
}
