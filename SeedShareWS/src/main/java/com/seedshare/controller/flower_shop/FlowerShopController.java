package com.seedshare.controller.flower_shop;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.FlowerShop;

/**
 * Controller interface of {@link com.seedshare.entity.FlowerShop}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface FlowerShopController extends BasicController<FlowerShop, Long> {

	ResponseEntity<?> save(FlowerShop flowerShop);

	ResponseEntity<?> findOneByCurrentUser();

	ResponseEntity<?> findOneByUser(Long id);

	ResponseEntity<?> findOneByCnpj(String cnpj);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);

}
