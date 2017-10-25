package com.seedshare.controller.growth;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.vegetable.Growth;

/**
 * Controller interface of {@link com.seedshare.entity.vegetable.Growth}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface GrowthController extends BasicController<Growth, Long> {

	ResponseEntity<?> findAll();

	ResponseEntity<?> update(Growth growth);

}
