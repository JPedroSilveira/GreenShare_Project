package com.seedshare.service.suggestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.UserUtils;
import com.seedshare.entity.Suggestion;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.SuggestionRepository;

/**
 * Implementation of  SuggestionService interface
 * @author joao.silva
 */
@Service
public class SuggestionServiceImpl extends UserUtils implements SuggestionService{

	@Autowired
	SuggestionRepository suggestionRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;
	
	@Override
	public boolean delete(Long id) {
		Suggestion suggestion = suggestionRepository.findOne(id);
		if(suggestion != null && suggestion.getUser().getId() == getCurrentUser().getId()) {
			suggestion.setIsActive(false);
			suggestionRepository.save(suggestion);
			return true;
		}
		return false;
	}

	@Override
	public Suggestion save(Suggestion suggestion) {
		Suggestion newSuggestion = new Suggestion(suggestion.getUser(),suggestion.getSpecies());
		if(newSuggestion.isValid()) {
			return suggestionRepository.save(newSuggestion);
		}
		return newSuggestion;
	}

	@Override
	public List<Suggestion> findByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suggestion findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Suggestion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
