package com.seedshare.controller.flower_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.FlowerShop;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.flower_shop.FlowerShopServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/flowerShop")
public class FlowerShopControllerImpl extends IsHelper implements FlowerShopController{
	
	@Autowired
	FlowerShopServiceImpl flowerShopService;
	
	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody FlowerShop flowerShop) {
		return flowerShopService.save(flowerShop);
    }

	@Override
	@GetMapping("/current_user/")
	public ResponseEntity<?> findOneByCurrentUser() {
		return flowerShopService.findOneByCurrentUser();
	}

	@Override
	@GetMapping("/user/{id}")
	public ResponseEntity<?> findOneByUser(@PathVariable Long id) {
		return flowerShopService.findOneByUser(id);
	}

	@Override
	@GetMapping("/{cnpj}")
	public ResponseEntity<?> findOneByCnpj(@PathVariable String cnpj) {
		return flowerShopService.findOneByCnpj(cnpj);
	}

	@Override
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody FlowerShop flowerShop) {
		return flowerShopService.update(flowerShop);
	}

	@Override
	@GetMapping("/state/{id}")
	public ResponseEntity<?> findAllByState(@PathVariable Long id) {
		return flowerShopService.findAllByState(id);
	}

	@Override
	@GetMapping("/city/{id}")
	public ResponseEntity<?> findAllByCity(@PathVariable Long id) {
		return flowerShopService.findAllByCity(id);
	}
}
