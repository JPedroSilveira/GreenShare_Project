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

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> save(Suggestion suggestion);

	ResponseEntity<?> findByUser();

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findAllByPage(Integer page, Integer size);
}
