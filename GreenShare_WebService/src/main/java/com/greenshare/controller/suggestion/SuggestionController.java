package com.greenshare.controller.suggestion;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.Suggestion;

/**
 * Interface controller of {@link com.greenshare.entity.Suggestion}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface SuggestionController {

	ResponseEntity<?> save(Suggestion suggestion);
	
	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findByCurrentUser();

	ResponseEntity<?> findAllByPage(Integer page, Integer size);

	ResponseEntity<?> enable(Long id);
	
}
