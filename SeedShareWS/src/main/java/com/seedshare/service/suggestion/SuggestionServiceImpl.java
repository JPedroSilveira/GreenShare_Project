package com.seedshare.service.suggestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Suggestion;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.SuggestionRepository;

/**
 * Implementation of  SuggestionService interface
 * @author joao.silva
 */
@Service
public class SuggestionServiceImpl extends IsHelper implements SuggestionService{

	@Autowired
	SuggestionRepository suggestionRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;
	
	@Override
	public boolean delete(long id) {
		Suggestion suggestion = suggestionRepository.findOne(id);
		if(isNotNull(suggestion) && suggestion.getUser().getId() == getCurrentUserId()) {
			suggestion.setIsActive(false);
			suggestionRepository.save(suggestion);
			return true;
		}
		return false;
	}

	@Override
	public Suggestion save(Suggestion suggestion) {
		Suggestion dbSuggestion = suggestionRepository.findOne(suggestion.getId());
		if(isNull(dbSuggestion)) {
			Suggestion newSuggestion = new Suggestion(suggestion.getUser(),suggestion.getSpecies());
			if(newSuggestion.isValid()) {
				return suggestionRepository.save(newSuggestion);
			}
		}
		return null;
	}

	@Override
	public List<Suggestion> findByUser() {
		return suggestionRepository.findByUser(getCurrentUser());
	}

	@Override
	public Suggestion findOne(long id) {
		return suggestionRepository.findOne(id);
	}

	@Override
	public List<Suggestion> findAll() {
		return (List<Suggestion>) suggestionRepository.findAll();
	}

}
