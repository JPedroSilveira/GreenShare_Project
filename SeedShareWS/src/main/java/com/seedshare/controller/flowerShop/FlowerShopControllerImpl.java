package com.seedshare.controller.flowerShop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.UserUtils;
import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;
import com.seedshare.service.flowerShop.FlowerShopServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/flowerShop")
public class FlowerShopControllerImpl extends UserUtils implements FlowerShopController{
	
	@Autowired
	FlowerShopServiceImpl flowerShopService;
	
	@Override
	@GetMapping("/get/")
	public ResponseEntity<?> getFlowerShopByUser() {
		User currentUser = getCurrentUser();
		if(currentUser.getIsLegalPerson()) {
			FlowerShop flowerShop = flowerShopService.findByUser(currentUser);
			if(flowerShop != null && flowerShop.getUser().getId() == getCurrentUser().getId()) {
				return new ResponseEntity<FlowerShop>(flowerShop, HttpStatus.OK);   
			}
		}
		return new ResponseEntity<String>("Usuário sem floricultura associada", HttpStatus.NOT_FOUND);   
    }
	
	@Override
	@PostMapping("/create/")
	public ResponseEntity<?> createFlowerShopByUser(@RequestBody FlowerShop flowerShop) {
		User currentUser = getCurrentUser();
		if(currentUser.getIsLegalPerson()) {
			FlowerShop newFlowerShop = new FlowerShop(flowerShop.getCnpj(),flowerShop.getDescription(),flowerShop.getLogoURL(),currentUser);
			if(newFlowerShop.generateNewValidation().isValid()) {
				if(flowerShopService.save(newFlowerShop) != null) {
					return new ResponseEntity<FlowerShop>(flowerShop, HttpStatus.OK);   
				}else {
					return new ResponseEntity<String>("Erro inesperado ao salvar floricultura", HttpStatus.INTERNAL_SERVER_ERROR);   
				}
			}else {
				return new ResponseEntity<List<String>>(flowerShop.getValidationErrors(), HttpStatus.OK);   
			}
		}
		return new ResponseEntity<String>("Usuário não é pessoa jurídica", HttpStatus.NOT_FOUND);   
    }
}
