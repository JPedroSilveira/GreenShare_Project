package com.seedshare.service.species;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Species;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.SpeciesRepository;

/**
 * Implementation of Species Service interface
 * @author joao.silva
 */
@Service
public class SpeciesServiceImpl extends IsHelper implements SpeciesService{

	@Autowired
    SpeciesRepository speciesRepository;

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if(isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			return isNotNull(speciesDB) ? new ResponseEntity<Species>(speciesDB, HttpStatus.OK)
					: new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOneByCommonName(String commonName) {
		if(commonName != null) {
			Iterable<Species> speciesDB = speciesRepository.findOneByCommonName(commonName);
			return new ResponseEntity<Iterable<Species>>(speciesDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nome da espécie não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findOneByScientificName(String scientificName) {
		if(isNotNull(scientificName)) {
			Iterable<Species> speciesDB = speciesRepository.findOneByScientificName(scientificName);
			return new ResponseEntity<Iterable<Species>>(speciesDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nome científico da espécie não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
