package com.seedshare.controller.suggestion;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Suggestion;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.suggestion.SuggestionServiceImpl;

/**
 * Implementation of  SuggestionController interface
 * @author joao.silva
 */
@RestController
@RequestMapping("/suggestion")
public class SuggestionControllerImpl extends IsHelper implements SuggestionController{

	@Autowired
	SuggestionServiceImpl suggestionService;
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return suggestionService.delete(id); 
	}

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody @Valid Suggestion suggestion) {
		return suggestionService.save(suggestion);
	}

	@Override
	@GetMapping("/findByUser")
	public ResponseEntity<?> findByUser() {
		return suggestionService.findByCurrentUser();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(long id) {
		return suggestionService.findOne(id);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		return suggestionService.findAll(); 
	}
	
}
