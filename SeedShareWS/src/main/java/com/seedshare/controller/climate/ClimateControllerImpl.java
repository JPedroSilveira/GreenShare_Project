package com.seedshare.controller.climate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.vegetable.Climate;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.climate.ClimateService;

/**
 * Controller class for Climate
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/climate")
public class ClimateControllerImpl extends IsHelper implements ClimateController {

	@Autowired
	ClimateService climateService;

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Climate climate) {
		return climateService.save(climate);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return climateService.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return climateService.findOne(id);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		return climateService.findAll();
	}

}
