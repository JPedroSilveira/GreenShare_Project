package com.seedshare.service.species;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Species;

/**
 * Service interface of Species
 * @author joao.silva
 */
public interface SpeciesService{

	ResponseEntity<?> findOneByCommonName(String commonName);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findOneByScientificName(String scientificName);

	ResponseEntity<?> activeSpecies(Long id);
	
	ResponseEntity<?> update(Species species);
	
}
