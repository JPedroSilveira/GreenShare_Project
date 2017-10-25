package com.seedshare.service.flower_shop;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.FlowerShop;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.FlowerShop}
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
