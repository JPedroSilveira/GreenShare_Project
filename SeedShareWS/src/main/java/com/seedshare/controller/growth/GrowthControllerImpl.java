package com.seedshare.controller.growth;

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

import com.seedshare.entity.vegetable.Growth;
import com.seedshare.service.growth.GrowthServiceImpl;

/**
 * Controller class of {@link com.seedshare.entity.vegetable.Growth}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@RestController
@RequestMapping("/growth/")
public class GrowthControllerImpl implements GrowthController {

	@Autowired
	GrowthServiceImpl growthService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Growth growth) {
		return growthService.save(growth);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return growthService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return growthService.findOne(id);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return growthService.findAll();
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Growth growth) {
		return growthService.update(growth);
	}

}
