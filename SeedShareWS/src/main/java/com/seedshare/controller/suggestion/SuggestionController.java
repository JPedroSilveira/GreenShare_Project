package com.seedshare.controller.suggestion;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Suggestion;

/**
 * Interface controller of {@link com.seedshare.entity.Suggestion}
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
