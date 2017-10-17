package com.seedshare.service.soil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Soil;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.SoilRepository;

/**
 * Implementation of {@link com.seedshare.service.request.SoilService} interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class SoilServiceImpl extends IsHelper implements SoilService {

	@Autowired
	SoilRepository soilRepository;

	@Override
	public ResponseEntity<?> save(Soil soil) {
		if (isNotNull(soil)) {
			Soil newSoil = new Soil(soil.getDescription(), soil.getName());
			return newSoil.isValid() ? new ResponseEntity<Soil>(soilRepository.save(newSoil), HttpStatus.OK)
					: new ResponseEntity<List<String>>(newSoil.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Solo não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			soilRepository.delete(id);
			return new ResponseEntity<String>("Solo deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Soil soilDB = soilRepository.findOne(id);
			if (isNotNull(soilDB)) {
				return new ResponseEntity<Soil>(soilDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Solo não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Soil> soilListDB = soilRepository.findAll();
		return new ResponseEntity<Iterable<Soil>>(soilListDB, HttpStatus.OK);
	}

}
