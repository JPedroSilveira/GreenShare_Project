package com.seedshare.controller.species;

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

import com.seedshare.entity.vegetable.Species;
import com.seedshare.service.species.SpeciesServiceImpl;

/**
 * Controller class of {@link com.seedshare.entity.vegetable.Species}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@RestController
@RequestMapping("/species/")
public class SpeciesControllerImpl implements SpeciesController{

	@Autowired
	SpeciesServiceImpl speciesService;
	
	@Override
	@GetMapping("common_name/{commonName}")
	public ResponseEntity<?> findOneByCommonName(@PathVariable String commonName) {
		return speciesService.findOneByCommonName(commonName);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return speciesService.findOne(id);
	}

	@Override
	@GetMapping("scientific_name/{scientificName}")
	public ResponseEntity<?> findOneByScientificName(@PathVariable String scientificName) {
		return speciesService.findOneByScientificName(scientificName);
	}

	@Override
	@PutMapping("enable/{id}")
	public ResponseEntity<?> enable(@PathVariable Long id) {
		return speciesService.enable(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Species species) {
		return speciesService.update(species);
	}

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Species species) {
		return speciesService.save(species);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return speciesService.delete(id);
	}

}
