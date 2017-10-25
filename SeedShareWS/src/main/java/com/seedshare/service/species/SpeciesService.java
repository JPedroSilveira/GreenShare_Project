package com.seedshare.service.species;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.vegetable.Species;

/**
 * Service interface of {@link com.seedshare.entity.vegetable.Species}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface SpeciesService {

	ResponseEntity<?> findOneByCommonName(String commonName);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findOneByScientificName(String scientificName);

	ResponseEntity<?> enable(Long id);

	ResponseEntity<?> update(Species species);

	ResponseEntity<?> save(Species species);

	ResponseEntity<?> delete(Long id);

}
