package com.greenshare.service.species;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.vegetable.Species;

/**
 * Service interface of {@link com.greenshare.entity.vegetable.Species}
 * 
 * @author joao.silva
 */
public interface SpeciesService {

	ResponseEntity<?> findOneByCommonName(String commonName);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findOneByScientificName(String scientificName);

	ResponseEntity<?> enable(Long id);

	ResponseEntity<?> update(Species species);

	ResponseEntity<?> save(Species species);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAll();

}
