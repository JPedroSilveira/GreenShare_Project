package com.greenshare.controller.climate;

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

import com.greenshare.entity.vegetable.Climate;
import com.greenshare.service.climate.ClimateService;

/**
 * Controller class of {@link com.greenshare.entity.vegetable.Climate}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@RestController
@RequestMapping("/climate/")
public class ClimateControllerImpl implements ClimateController {

	@Autowired
	ClimateService climateService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Climate climate) {
		return climateService.save(climate);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return climateService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return climateService.findOne(id);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return climateService.findAll();
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Climate climate) {
		return climateService.update(climate);
	}

}
