package com.seedshare.service.suggestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Species;
import com.seedshare.entity.Suggestion;
import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.SuggestionRepository;

/**
 * Implementation of SuggestionService interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class SuggestionServiceImpl extends IsHelper implements SuggestionService {

	@Autowired
	SuggestionRepository suggestionRepository;

	@Autowired
	SpeciesRepository speciesRepository;
	
	private static final int MAX_PAGE_SIZE = 100; 

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			Suggestion suggestionDB = suggestionRepository.findOne(id);
			if (isNotNull(suggestionDB) && suggestionDB.getUser().getId() == getCurrentUserId()) {
				suggestionDB.setIsActive(false);
				suggestionRepository.save(suggestionDB);
				return new ResponseEntity<String>("Sugestão desativada", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Sugestão não pertence ao usuário atual.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> save(Suggestion suggestion) {
		if (isNotNull(suggestion)) {
			Suggestion newSuggestion = new Suggestion(suggestion.getUser(), suggestion.getSpecies());
			if (newSuggestion.isValid()) {
				Species species = newSuggestion.getSpecies();
				Species newSpecies = new Species(species.getAttractBirds(), species.getDescription(),
						species.getCultivationGuide(), species.getIsMedicinal(), species.getAttractBees(),
						species.getScientificName(), species.getCommonName(), species.getIsOrnamental(),
						species.getAverageHeight(), species.getGrowth());
				if (newSpecies.isValid()) {
					newSuggestion.getUser().clearPrivateData();
					return new ResponseEntity<Suggestion>(newSuggestion, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(newSpecies.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<String>>(newSuggestion.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByCurrentUser() {
		User currentUser = getCurrentUser();
		if(isNotNull(currentUser)) {
			Iterable<Suggestion> suggestionListDB = suggestionRepository.findByUser(getCurrentUser());
			suggestionListDB.forEach(s -> s.getUser().clearPrivateData());
			return new ResponseEntity<Iterable<Suggestion>>(suggestionListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nenhum usuário logado.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if(isNotNull(id)) {
			Suggestion suggestionDB = suggestionRepository.findOne(id);
			if(isNotNull(suggestionDB)) {
				return new ResponseEntity<Suggestion>(suggestionDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Sugestão não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByPage(Integer page, Integer size) {
		if(isNotNull(page) && isNotNull(size) && is(size).smallerOrEqual(MAX_PAGE_SIZE)) {
			Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "lastModificationDate"));
			Iterable<Suggestion> suggestionListDB = suggestionRepository.findAll(pageable);
			return new ResponseEntity<Iterable<Suggestion>>(suggestionListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

}
