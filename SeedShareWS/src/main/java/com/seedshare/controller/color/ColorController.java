package com.seedshare.controller.color;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Color;

/**
 * Controller interface of {@link com.seedshare.entity.vegetable.Color}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface ColorController extends BasicController<Color, Long> {

	ResponseEntity<?> findAll();

	ResponseEntity<?> update(Color color);

}
