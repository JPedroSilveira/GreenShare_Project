package com.greenshare.controller.country;

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

import com.greenshare.entity.address.Country;
import com.greenshare.service.country.CountryService;

/**
 * Controller class of {@link com.greenshare.entity.address.Country}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@RestController
@RequestMapping("/country/")
public class CountryControllerImpl implements CountryService {

	@Autowired
	CountryService countryService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Country country) {
		return countryService.save(country);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return countryService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return countryService.findOne(id);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return countryService.findAll();
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Country country) {
		return countryService.update(country);
	}

}
