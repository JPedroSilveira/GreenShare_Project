package com.seedshare.service.suggestion;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Suggestion;

/**
 * Interface of Suggestion service 
 * @author joao.silva
 */
public interface SuggestionService {
	
	ResponseEntity<?> save(Suggestion suggestion);
		
	ResponseEntity<?> findOne(Long id);
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findByCurrentUser();
}
