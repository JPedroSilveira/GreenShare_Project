package com.greenshare.service.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.address.Country;
import com.greenshare.entity.address.State;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.CountryRepository;
import com.greenshare.repository.StateRepository;

/**
 * Service class of Country
 * 
 * @author joao.silva
 */
@Service
public class StateServiceImpl extends IsHelper implements StateService {

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public ResponseEntity<?> save(State state) {
		if (isNotNull(state) && isNotNull(state.getCountry())) {
			Country countryDB = countryRepository.findOne(state.getCountry().getId());
			if(isNotNull(countryDB)) {
				State stateDB = stateRepository.findOneByCountryAndNameIgnoreCase(countryDB.getId(),
						state.getName());
				if (isNull(stateDB)) {
					State newState = new State(state.getName(), state.getCountry());
					return newState.isValid() ? new ResponseEntity<State>(stateRepository.save(newState), HttpStatus.OK)
							: new ResponseEntity<List<String>>(newState.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Nome de estado já cadastrado para este pais.", HttpStatus.CONFLICT);	
			}
			return new ResponseEntity<String>("País do estado não encontrado.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Estado não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			stateRepository.delete(id);
			return new ResponseEntity<String>("Estado deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			State stateDB = stateRepository.findOne(id);
			if (isNotNull(stateDB)) {
				return new ResponseEntity<State>(stateDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Estado não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<State> stateListDB = stateRepository.findAll();
		return new ResponseEntity<Iterable<State>>(stateListDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findAllByCountry(Long id) {
		Iterable<State> stateListDB = stateRepository.findAllByCountryId(id);
		return new ResponseEntity<Iterable<State>>(stateListDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(State state) {
		// TODO Auto-generated method stub
		return null;
	}

}
