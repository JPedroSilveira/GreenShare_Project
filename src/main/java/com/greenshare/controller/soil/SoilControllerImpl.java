package com.greenshare.controller.soil;

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

import com.greenshare.entity.vegetable.Soil;
import com.greenshare.service.soil.SoilServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.vegetable.Soil}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/soil/")
public class SoilControllerImpl implements SoilController {

	@Autowired
	SoilServiceImpl soilService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Soil soil) {
		return soilService.save(soil);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return soilService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return soilService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Soil soil) {
		return soilService.update(soil);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return soilService.findAll();
	}
	
}
