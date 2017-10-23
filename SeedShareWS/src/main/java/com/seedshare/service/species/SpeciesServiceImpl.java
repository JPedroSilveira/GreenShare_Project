package com.seedshare.service.species;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.vegetable.Species;
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
					: new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOneByCommonName(String commonName) {
		if(isNotNull(commonName)) {
			Iterable<Species> speciesListDB = speciesRepository.findOneByCommonNameAndActiveTrue(commonName);
			return new ResponseEntity<Iterable<Species>>(speciesListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nome da espécie não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findOneByScientificName(String scientificName) {
		if(isNotNull(scientificName)) {
			Iterable<Species> speciesListDB = speciesRepository.findOneByScientificNameAndActiveTrue(scientificName);
			return new ResponseEntity<Iterable<Species>>(speciesListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nome científico da espécie não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> activeSpecies(Long id) {
		if(isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			if(isNotNull(speciesDB)) {
				speciesDB.activate();
				speciesDB = speciesRepository.save(speciesDB);
				return new ResponseEntity<Species>(speciesDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Species species) {
		if (isNotNull(species)) {
			Species speciesDB = speciesRepository.findOne(species.getId());
			if (isNotNull(speciesDB)) {
				speciesDB.update(species);
				if (speciesDB.isValid()) {
					speciesDB = speciesRepository.save(speciesDB);
					return new ResponseEntity<Species>(speciesDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(speciesDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Espécie não pode ser nula.", HttpStatus.BAD_REQUEST);
	}
}
