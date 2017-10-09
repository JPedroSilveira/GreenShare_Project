package com.seedshare.controller.flower;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Flower;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.flower.FlowerService;

/**
 * Controller class for Flower
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/flower")
public class FlowerControllerImpl extends IsHelper implements FlowerController {

	@Autowired
	FlowerService flowerService;

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody @Valid Flower flower) {
		return flowerService.save(flower);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(Long id) {
		return flowerService.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(Long id) {
		return flowerService.findOne(id);
	}

}
