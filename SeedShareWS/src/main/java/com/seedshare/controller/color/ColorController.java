package com.seedshare.controller.color;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.Color;

/**
 * Controller interface for Color
 * 
 * @author joao.silva
 */
public interface ColorController extends BasicController<Color, Long> {
	ResponseEntity<?> findAll();
}
