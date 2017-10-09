package com.seedshare.service.flower_shop;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.FlowerShop;
import com.seedshare.service.BasicService;

/**
 * Service interface of FlowerShop
 * 
 * @author joao.silva
 */
public interface FlowerShopService extends BasicService<FlowerShop, Long> {

	ResponseEntity<?> findByCnpj(String cnpj);

	ResponseEntity<?> findByUser(Long id);

	ResponseEntity<?> findByCurrentUser();

	ResponseEntity<?> update(FlowerShop flowerShop);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);
}
