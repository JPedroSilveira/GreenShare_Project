package com.seedshare.controller.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.address.Country;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.country.CountryService;

/**
 * Controller class for Country
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/country")
public class CountryControllerImpl extends IsHelper implements CountryService {

	@Autowired
	CountryService countryService;

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Country country) {
		return countryService.save(country);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return countryService.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return countryService.findOne(id);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		return countryService.findAll();
	}

	@Override
	public ResponseEntity<?> update(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

}
