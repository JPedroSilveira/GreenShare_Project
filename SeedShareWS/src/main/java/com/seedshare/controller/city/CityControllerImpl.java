package com.seedshare.controller.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.address.City;
import com.seedshare.service.city.CityServiceImpl;

/**
 * Controller Class for City
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/city")
public class CityControllerImpl implements CityController {

	@Autowired
	CityServiceImpl cityService;

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody City city) {
		return cityService.save(city);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return cityService.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return cityService.findOne(id);
	}

	@Override
	@GetMapping("/state/{id}")
	public ResponseEntity<?> findByState(@PathVariable Long id) {
		return cityService.findByState(id);
	}

	@Override
	@GetMapping("/country/{id}")
	public ResponseEntity<?> findByCountry(@PathVariable Long id) {
		return cityService.findByCountry(id);
	}

}
