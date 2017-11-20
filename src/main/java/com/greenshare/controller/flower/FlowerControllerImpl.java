package com.greenshare.controller.flower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenshare.entity.vegetable.Flower;
import com.greenshare.service.flower.FlowerServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.vegetable.Flower}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/flower/")
public class FlowerControllerImpl implements FlowerController {

	@Autowired
	FlowerServiceImpl flowerService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Flower flower) {
		return flowerService.save(flower);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return flowerService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return flowerService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Flower flower) {
		return flowerService.update(flower);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return flowerService.findAll();
	}

	@Override
	@GetMapping("species/{id}")
	public ResponseEntity<?> findOneBySpecies(@PathVariable Long id) {
		return flowerService.findOneBySpecies(id);
	}

}
