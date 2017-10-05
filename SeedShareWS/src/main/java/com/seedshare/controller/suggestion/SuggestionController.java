package com.seedshare.controller.suggestion;

import org.springframework.http.ResponseEntity;

public interface SuggestionController {
	/*Only the user that create the Suggestion can delete*/
	ResponseEntity<?> delete(Long id);
	/*Anyone can create a Suggestion and save, only the user that create the suggestion can update*/
	ResponseEntity<?> save(Long id);
	/*Find all controller associated with a user*/
	ResponseEntity<?> findByUser();
	/*Find one by id, anyone can find any suggestion*/
	ResponseEntity<?> findOne(Long id);
	/*Anyone can findAll suggestion*/
	ResponseEntity<?> findAll();
}
