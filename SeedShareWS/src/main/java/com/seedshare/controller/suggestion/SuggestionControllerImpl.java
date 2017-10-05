package com.seedshare.controller.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.seedshare.service.species.SpeciesServiceImpl;
import com.seedshare.service.suggestion.SuggestionServiceImpl;

public class SuggestionControllerImpl implements SuggestionController{

	@Autowired
	SuggestionServiceImpl suggestionServiceImpl;
	
	@Autowired
	SpeciesServiceImpl speciesServiceImpl;
	
	@Override
	public ResponseEntity<?> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> save(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
