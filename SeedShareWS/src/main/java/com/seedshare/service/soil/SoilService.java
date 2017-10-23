package com.seedshare.service.soil;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Soil;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Soil}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface SoilService extends BasicService<Soil, Long> {

	ResponseEntity<?> findAll();

}
