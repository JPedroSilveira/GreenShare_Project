package com.seedshare.service.climate;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Climate;
import com.seedshare.service.BasicService;

/**
 * Service interface of Climate
 * 
 * @author joao.silva
 */
public interface ClimateService extends BasicService<Climate, Long> {
	ResponseEntity<?> findAll();
}
