package com.greenshare.service.flower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.Month;
import com.greenshare.entity.vegetable.Flower;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.FlowerRepository;
import com.greenshare.repository.MonthRepository;

/**
 * Service implementation of {@link com.greenshare.service.flower.FlowerService}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class FlowerServiceImpl extends IsHelper implements FlowerService {

	@Autowired
	FlowerRepository flowerRepository;
	
	@Autowired
	MonthRepository monthRepository;

	@Override
	public ResponseEntity<?> save(Flower flower) {
		if (isNotNull(flower)) {
			List<Month> MonthListDB = monthRepository.findAllByNumberIn(flower.getMonthNumbers());
			Flower newFlower = new Flower(flower.getIsAromatic(), flower.getDescription(), flower.getName(),
					flower.getSpecies(), flower.getColors(), MonthListDB);
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

	@Override
	public ResponseEntity<?> findOneBySpecies(Long id) {
		if (isNotNull(id)) {
			Flower flowerDB = flowerRepository.findOneBySpecies(id);
			return new ResponseEntity<Flower>(flowerDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Flower flower) {
		if (isNotNull(flower)) {
			Flower flowerDB = flowerRepository.findOne(flower.getId());
			if (isNotNull(flowerDB)) {
				flowerDB.update(flower);
				if (flowerDB.isValid()) {
					flowerDB = flowerRepository.save(flowerDB);
					return new ResponseEntity<Flower>(flowerDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(flowerDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("País não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("País não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
