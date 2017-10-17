package com.seedshare.service.flower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Flower;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FlowerRepository;

/**
 * Service class of Flower
 * 
 * @author joao.silva
 */
@Service
public class FlowerServiceImpl extends IsHelper implements FlowerService {

	@Autowired
	FlowerRepository flowerRepository;

	@Override
	public ResponseEntity<?> save(Flower flower) {
		if (isNotNull(flower)) {
			Flower newFlower = new Flower(flower.getIsAromatic(), flower.getDescription(), flower.getName(),
					flower.getSpecies());
			return newFlower.isValid() ? new ResponseEntity<Flower>(flowerRepository.save(newFlower), HttpStatus.OK)
					: new ResponseEntity<List<String>>(newFlower.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Cor inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			flowerRepository.delete(id);
			return new ResponseEntity<String>("Flor deletada.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Flower flowerDB = flowerRepository.findOne(id);
			if (isNotNull(flowerDB)) {
				return new ResponseEntity<Flower>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Flor não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Flower> flowersDB = flowerRepository.findAll();
		return new ResponseEntity<Iterable<Flower>>(flowersDB, HttpStatus.OK);
	}
}
