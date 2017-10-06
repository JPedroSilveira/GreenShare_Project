package com.seedshare.controller.flowerShop;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;

/**
 * @author joao.silva
 */
public interface FlowerShopController{

	ResponseEntity<?> saveFlowerShopByUser(FlowerShop flowerShop);

	ResponseEntity<?> getFlowerShopByCurrentUser();

	ResponseEntity<?> getFlowerShopByUser(User user);

	ResponseEntity<?> getFlowerShopByCNPJ(String cnpj);
}
