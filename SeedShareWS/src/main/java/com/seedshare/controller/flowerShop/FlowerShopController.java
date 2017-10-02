package com.seedshare.controller.flowerShop;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.FlowerShop;

/**
 * @author joao.silva
 */
public interface FlowerShopController{
	ResponseEntity<?> getFlowerShopByUser();
	
	ResponseEntity<?> createFlowerShopByUser(FlowerShop flowerShop);
}
