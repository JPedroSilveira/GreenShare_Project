package com.seedshare.service.growth;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Growth;
import com.seedshare.service.BasicService;

/**
 * Service interface of Growth
 * 
 * @author joao.silva
 */
public interface GrowthService extends BasicService<Growth, Long>{

	ResponseEntity<?> findAll();

}
