package com.seedshare.controller.flowerShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.flowerShop.FlowerShopServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/flowerShop")
public class FlowerShopControllerImpl extends IsHelper implements FlowerShopController{
	
	@Autowired
	FlowerShopServiceImpl flowerShopService;
	
	@Override
	@GetMapping("/current_user/")
	public ResponseEntity<?> getFlowerShopByCurrentUser() {
		FlowerShop flowerShop = flowerShopService.findByCurrentUser();
		if(isNotNull(flowerShop) && flowerShop.getUser().getId() == getCurrentUserId()) {
			return new ResponseEntity<FlowerShop>(flowerShop, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Usuário sem floricultura associada.", HttpStatus.NOT_FOUND);   
    }
	
	@Override
	@GetMapping("/user")
	public ResponseEntity<?> getFlowerShopByUser(@RequestBody User user) {
		FlowerShop flowerShop = flowerShopService.findByUser(user.getId());
		if(isNotNull(flowerShop) && flowerShop.getUser().getId() == getCurrentUserId()) {
			return new ResponseEntity<FlowerShop>(flowerShop, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Usuário sem floricultura associada.", HttpStatus.NOT_FOUND);   
    }
	
	@Override
	@GetMapping("/{cnpj}")
	public ResponseEntity<?> getFlowerShopByCNPJ(@PathVariable String cnpj) {
		FlowerShop flowerShop = flowerShopService.findByCnpj(cnpj);
		if(isNotNull(flowerShop) && flowerShop.getUser().getId() == getCurrentUserId()) {
			return new ResponseEntity<FlowerShop>(flowerShop, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Usuário sem floricultura associada.", HttpStatus.NOT_FOUND);   
    }
	
	@Override
	@PostMapping("/")
	public ResponseEntity<?> saveFlowerShopByUser(@RequestBody FlowerShop flowerShop) {
		FlowerShop response = flowerShopService.save(flowerShop);
		if(isNotNull(response)) {
			return new ResponseEntity<FlowerShop>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Erro ao salvar ou atualizar floricultura.", HttpStatus.NOT_FOUND);   
    }
}
