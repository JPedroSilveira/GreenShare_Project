package com.seedshare.service.suggestion;

import java.util.List;

import com.seedshare.entity.Suggestion;

/**
 * Interface of Suggestion service 
 * @author joao.silva
 */
public interface SuggestionService {
	
	boolean delete(Long id);
	
	Suggestion save(Suggestion suggestion);
	
	List<Suggestion> findByUser();
	
	Suggestion findOne(Long id);
	
	List<Suggestion> findAll();
}
