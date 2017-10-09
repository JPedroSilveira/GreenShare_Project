package com.seedshare.service.color;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Color;
import com.seedshare.service.BasicService;

/**
 * Service interface of Color
 * 
 * @author joao.silva
 */
public interface ColorService extends BasicService<Color, Long> {
	ResponseEntity<?> findAll();
}
