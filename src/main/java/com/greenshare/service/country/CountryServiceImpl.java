package com.greenshare.service.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.address.Country;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.CountryRepository;

/**
 * Service implementation of
 * {@link com.greenshare.service.country.CountryService}
 * 
 * @author joao.silva
 */
@Service
public class CountryServiceImpl extends IsHelper implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Override
	public ResponseEntity<?> save(Country country) {
		if (isNotNull(country)) {
			Country countryDB = countryRepository.findOneByName(country.getName());
			if (isNull(countryDB)) {
				Country newCountry = new Country(country.getName());
				return newCountry.isValid()
						? new ResponseEntity<Country>(countryRepository.save(newCountry), HttpStatus.OK)
						: new ResponseEntity<List<String>>(newCountry.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Nome de pais já cadastrado.", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Pais não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			countryRepository.delete(id);
			return new ResponseEntity<String>("Pais deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Country countryDB = countryRepository.findOne(id);
			if (isNotNull(countryDB)) {
				return new ResponseEntity<Country>(countryDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Pais não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Country> countriesDB = countryRepository.findAll();
		//return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Iterable<Country>>(countriesDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(Country country) {
		if (isNotNull(country)) {
			Country countryDB = countryRepository.findOne(country.getId());
			if (isNotNull(countryDB)) {
				countryDB.update(country);
				if (countryDB.isValid()) {
					countryDB = countryRepository.save(countryDB);
					return new ResponseEntity<Country>(countryDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(countryDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("País não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("País não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
