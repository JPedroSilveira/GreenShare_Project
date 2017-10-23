package com.seedshare.service.climate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.vegetable.Climate;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.ClimateRepository;

/**
 * Implementation Service of
 * {@link com.seedshare.service.climate.ClimateService}
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
			return newClimate.isValid() ? new ResponseEntity<Climate>(climateRepository.save(newClimate), HttpStatus.OK)
					: new ResponseEntity<List<String>>(newClimate.getValidationErrors(), HttpStatus.BAD_REQUEST);
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

	@Override
	public ResponseEntity<?> update(Climate climate) {
		if (isNotNull(climate)) {
			Climate climateDB = climateRepository.findOne(climate.getId());
			if (isNotNull(climateDB)) {
				climateDB.update(climate);
				if (climateDB.isValid()) {
					climateDB = climateRepository.save(climateDB);
					return new ResponseEntity<Climate>(climateDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(climateDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Clima não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Clima não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
