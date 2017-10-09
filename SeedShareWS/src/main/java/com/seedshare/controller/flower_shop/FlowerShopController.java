package com.seedshare.controller.flower_shop;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.FlowerShop;

/**
 * FlowerShop interface Controller
 * 
 * @author joao.silva
 */
public interface FlowerShopController {

	ResponseEntity<?> save(FlowerShop flowerShop);

	ResponseEntity<?> findByCurrentUser();

	ResponseEntity<?> findByUser(Long id);

	ResponseEntity<?> findByCnpj(String cnpj);

	ResponseEntity<?> update(FlowerShop flowerShop);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);
}
