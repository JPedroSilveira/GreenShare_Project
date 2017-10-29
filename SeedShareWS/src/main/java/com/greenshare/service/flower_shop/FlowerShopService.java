package com.greenshare.service.flower_shop;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.FlowerShop;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.FlowerShop}
 * 
 * @author joao.silva
 */
public interface FlowerShopService extends BasicService<FlowerShop, Long> {

	ResponseEntity<?> findOneByCnpj(String cnpj);

	ResponseEntity<?> findOneByUser(Long id);

	ResponseEntity<?> findOneByCurrentUser();
	
	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);

}
