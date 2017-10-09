package com.seedshare.service.climate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Climate;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.ClimateRepository;

/**
 * Service class of Climate
 * 
 * @author joao.silva
 */
@Service
public class ClimateServiceImpl extends IsHelper implements ClimateService {

	@Autowired
	ClimateRepository climateRepository;

	@Override
	public ResponseEntity<?> save(Climate climate) {
		if (isNotNull(climate)) {
			Climate newClimate = new Climate(climate.getDescription(), climate.getName());
			if (newClimate.isValid()) {
				newClimate = climateRepository.save(newClimate);
				return new ResponseEntity<Climate>(newClimate, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Clima inválido.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			climateRepository.delete(id);
			return new ResponseEntity<String>("Clima deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Climate climateDB = climateRepository.findOne(id);
			if (isNotNull(climateDB)) {
				return new ResponseEntity<Climate>(climateDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Clima não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Climate> climatesDB = climateRepository.findAll();
		return new ResponseEntity<Iterable<Climate>>(climatesDB, HttpStatus.OK);
	}

}
