package com.seedshare.service.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Country;
import com.seedshare.entity.State;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.StateRepository;

/**
 * Service class of Country
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class StateServiceImpl extends IsHelper implements StateService {

	@Autowired
	StateRepository stateRepository;

	@Override
	public ResponseEntity<?> save(State state) {
		if (isNotNull(state)) {
			State stateDB = stateRepository.findOneByCountryAndNameContainsIgnoreCase(state.getCountry(),
					state.getName());
			if (isNull(stateDB)) {
				State newState = new State(state.getName(), state.getCountry());
				return newState.isValid() ? new ResponseEntity<State>(stateRepository.save(newState), HttpStatus.OK)
						: new ResponseEntity<List<String>>(newState.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Nome de estado já cadastrado para este pais.", HttpStatus.CONFLICT);
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
	public ResponseEntity<?> findAllByCountry(Country country) {
		Iterable<State> stateListDB = stateRepository.findAllByCountry(country);
		return new ResponseEntity<Iterable<State>>(stateListDB, HttpStatus.OK);
	}

}
