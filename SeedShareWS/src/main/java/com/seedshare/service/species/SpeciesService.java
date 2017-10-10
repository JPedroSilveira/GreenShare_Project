package com.seedshare.service.species;

import org.springframework.http.ResponseEntity;

/**
 * Service interface of Species
 * @author joao.silva
 */
public interface SpeciesService{

	ResponseEntity<?> findOneByCommonName(String commonName);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findOneByScientificName(String scientificName);

}
