package com.seedshare.controller.fruit;

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

import com.seedshare.entity.vegetable.Fruit;
import com.seedshare.service.fruit.FruitServiceImpl;

/**
 * Controller class of {@link com.seedshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@RestController
@RequestMapping("/fruit/")
public class FruitControllerImpl implements FruitController {

	@Autowired
	FruitServiceImpl fruitService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Fruit fruit) {
		return fruitService.save(fruit);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return fruitService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return fruitService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Fruit fruit) {
		return fruitService.update(fruit);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return fruitService.findAll();
	}

	@Override
	@GetMapping("species/{id}")
	public ResponseEntity<?> findOneBySpecies(@PathVariable Long id) {
		return fruitService.findOneBySpecies(id);
	}

}
