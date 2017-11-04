package com.greenshare.controller.growth;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.vegetable.Growth;

/**
 * Controller interface of {@link com.greenshare.entity.vegetable.Growth}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface GrowthController extends BasicController<Growth, Long> {

	ResponseEntity<?> findAll();

	ResponseEntity<?> update(Growth growth);

}
