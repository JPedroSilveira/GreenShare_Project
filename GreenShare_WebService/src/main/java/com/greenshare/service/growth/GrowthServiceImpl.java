package com.greenshare.service.growth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.vegetable.Growth;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.GrowthRepository;

/**
 * Service implementation of {@link com.greenshare.service.growth.GrowthService}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class GrowthServiceImpl extends IsHelper implements GrowthService {

	@Autowired
	GrowthRepository growthRepository;

	@Override
	public ResponseEntity<?> save(Growth growth) {
		if (isNotNull(growth)) {
			Growth newGrowth = new Growth(growth.getName(), growth.getDescription());
			return newGrowth.isValid() ? new ResponseEntity<Growth>(growthRepository.save(newGrowth), HttpStatus.OK)
					: new ResponseEntity<List<String>>(newGrowth.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Cor inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			growthRepository.delete(id);
			return new ResponseEntity<String>("Nível de crescimento deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Growth growthDB = growthRepository.findOne(id);
			if (isNotNull(growthDB)) {
				return new ResponseEntity<Growth>(growthDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nível de crescimento não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Growth> growthListDB = growthRepository.findAll();
		return new ResponseEntity<Iterable<Growth>>(growthListDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(Growth growth) {
		if (isNotNull(growth)) {
			Growth growthDB = growthRepository.findOne(growth.getId());
			if (isNotNull(growthDB)) {
				growthDB.update(growth);
				if (growthDB.isValid()) {
					growthDB = growthRepository.save(growthDB);
					return new ResponseEntity<Growth>(growthDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(growthDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Nível de crescimento não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Nível de crescimento não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
