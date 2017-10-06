package com.seedshare.controller.suggestion;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Suggestion;

/**
 * Interface of  SuggestionController
 * @author joao.silva
 */
public interface SuggestionController {
	/*Only the user that create the Suggestion can delete*/
	ResponseEntity<?> delete(long id);
	/*Anyone can create a Suggestion and save*/
	ResponseEntity<?> save(Suggestion suggestion);
	/*Find all controller associated with a user*/
	ResponseEntity<?> findByUser();
	/*Find one by id, anyone can find any suggestion*/
	ResponseEntity<?> findOne(long id);
	/*Anyone can findAll suggestion*/
	ResponseEntity<?> findAll();
}
