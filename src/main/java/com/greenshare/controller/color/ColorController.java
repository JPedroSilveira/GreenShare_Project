package com.greenshare.controller.color;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.vegetable.Color;

/**
 * Controller interface of {@link com.greenshare.entity.vegetable.Color}
 * 
 * @author joao.silva
 */
public interface ColorController extends BasicController<Color, Long> {

	ResponseEntity<?> findAll();

	ResponseEntity<?> update(Color color);

}
