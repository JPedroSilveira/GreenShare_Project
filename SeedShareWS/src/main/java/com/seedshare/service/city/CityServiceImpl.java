package com.seedshare.service.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.address.City;
import com.seedshare.entity.address.State;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.CityRepository;
import com.seedshare.repository.CountryRepository;
import com.seedshare.repository.StateRepository;

/**
 * Implementation Service of {@link com.seedshare.service.city.CityService}
 * 
 * @author joao.silva
 */
@Service
public class CityServiceImpl extends IsHelper implements CityService {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CountryRepository countryRepository;

	@Override
	public ResponseEntity<?> save(City city) {
		if (isNotNull(city)) {
			State state = city.getState();
			if(isNotNull(state) && isNotNull(state.getId())) {
				state = stateRepository.findOne(state.getId());
				if(isNotNull(state)) {
					City cityDB = cityRepository.findOneByStateAndNameIgnoreCase(state.getId(), city.getName());
					if(isNull(cityDB)) {
						City newCity = new City(city.getName(), state);
						return newCity.isValid() ? new ResponseEntity<City>(cityRepository.save(newCity), HttpStatus.OK)
								: new ResponseEntity<List<String>>(newCity.getValidationErrors(), HttpStatus.BAD_REQUEST);
					}
					return new ResponseEntity<String>("Cidade com este nome e estado já cadastrada.", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Estado não encontrado.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Estado não pode ser nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Cidade não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			cityRepository.delete(id);
			return new ResponseEntity<String>("Cidade deletada.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			City cityDB = cityRepository.findOne(id);
			if (isNotNull(cityDB)) {
				return new ResponseEntity<City>(cityDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByState(Long id) {
		if (isNotNull(id)) {
			Iterable<City> cityListDB = cityRepository.findAllByState(id);
			return new ResponseEntity<Iterable<City>>(cityListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByCountry(Long id) {
		if (isNotNull(id)) {
			Iterable<City> cityListDB = cityRepository.findAllByStateCountry(id);
			return new ResponseEntity<Iterable<City>>(cityListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(City city) {
		if(isNotNull(city)) {
			City cityDB = cityRepository.findOne(city.getId());
			if(isNotNull(cityDB)) {
				cityDB.update(city);
				if(cityDB.isValid()) {
					cityDB = cityRepository.save(cityDB);
					return new ResponseEntity<City>(cityDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(cityDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Cidade não pode ser nula.", HttpStatus.BAD_REQUEST);
	}
}
