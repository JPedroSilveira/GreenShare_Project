package com.seedshare.service.fruit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Fruit;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FruitRepository;

/**
 * Service class of Fruit
 * 
 * @author joao.silva
 */
@Service
public class FruitServiceImpl extends IsHelper implements FruitService{

	@Autowired
	FruitRepository fruitRepository;	
	
	@Override
	public ResponseEntity<?> save(Fruit fruit) {
		if (isNotNull(fruit)) {
			Fruit newFruit = new Fruit(fruit.getFaunaConsumption(), fruit.getHumanConsumption(), fruit.getDescription(), fruit.getName(), fruit.getSpecies());
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

}
