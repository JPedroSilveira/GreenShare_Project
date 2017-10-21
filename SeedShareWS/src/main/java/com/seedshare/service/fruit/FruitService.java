package com.seedshare.service.fruit;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Fruit;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Fruit}
 * 
 * @author joao.silva
 */
public interface FruitService extends BasicService<Fruit, Long> {

	ResponseEntity<?> findAll();

}
