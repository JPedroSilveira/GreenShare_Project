package com.greenshare.controller.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenshare.entity.address.State;
import com.greenshare.service.state.StateServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.address.State}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/state/")
public class StateControllerImpl implements StateController{

	@Autowired
	StateServiceImpl stateService;
	
	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody State state) {
		return stateService.save(state);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return stateService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return stateService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody State state) {
		return stateService.update(state);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return stateService.findAll();
	}

	@Override
	@GetMapping("country/{id}")
	public ResponseEntity<?> findAllByCountry(@PathVariable Long id) {
		return stateService.findAllByCountry(id);
	}

}
