package com.greenshare.service.fruit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.Month;
import com.greenshare.entity.vegetable.Fruit;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.FruitRepository;
import com.greenshare.repository.MonthRepository;

/**
 * Service implementation of {@link com.greenshare.service.fruit.FruitService}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class FruitServiceImpl extends IsHelper implements FruitService {

	@Autowired
	FruitRepository fruitRepository;
	
	@Autowired
	MonthRepository monthRepository;

	@Override
	public ResponseEntity<?> save(Fruit fruit) {
		if (isNotNull(fruit)) {
			List<Month> MonthListDB = monthRepository.findAllByNumberIn(fruit.getMonthNumbers());
			Fruit newFruit = new Fruit(fruit.getFaunaConsumption(), fruit.getHumanConsumption(), MonthListDB, fruit.getDescription(),
					fruit.getName());
			return newFruit.isValid() ? new ResponseEntity<Fruit>(fruitRepository.save(newFruit), HttpStatus.OK)
					: new ResponseEntity<List<String>>(newFruit.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Fruto não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			fruitRepository.delete(id);
			return new ResponseEntity<String>("Fruto deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Fruit fruitDB = fruitRepository.findOne(id);
			if (isNotNull(fruitDB)) {
				return new ResponseEntity<Fruit>(fruitDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Fruto não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Fruit> fruitListDB = fruitRepository.findAll();
		return new ResponseEntity<Iterable<Fruit>>(fruitListDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findOneBySpecies(Long id) {
		if (isNotNull(id)) {
			Fruit fruitDB = fruitRepository.findOneBySpecies(id);
			return new ResponseEntity<Fruit>(fruitDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Fruit fruit) {
		if (isNotNull(fruit)) {
			Fruit fruitDB = fruitRepository.findOne(fruit.getId());
			if (isNotNull(fruitDB)) {
				fruitDB.update(fruit);
				if (fruitDB.isValid()) {
					fruitDB = fruitRepository.save(fruitDB);
					return new ResponseEntity<Fruit>(fruitDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(fruitDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("País não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("País não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
