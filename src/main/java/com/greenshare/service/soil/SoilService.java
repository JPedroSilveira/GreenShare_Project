package com.greenshare.service.soil;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Soil;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Soil}
 * 
 * @author joao.silva
 */
public interface SoilService extends BasicService<Soil, Long> {

	ResponseEntity<?> findAll();

}
