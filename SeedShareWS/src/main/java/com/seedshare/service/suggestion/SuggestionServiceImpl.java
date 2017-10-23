package com.seedshare.service.suggestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Month;
import com.seedshare.entity.Suggestion;
import com.seedshare.entity.user.User;
import com.seedshare.entity.vegetable.Flower;
import com.seedshare.entity.vegetable.Fruit;
import com.seedshare.entity.vegetable.Species;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FlowerRepository;
import com.seedshare.repository.FruitRepository;
import com.seedshare.repository.MonthRepository;
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

	@Autowired
	FruitRepository fruitRepository;

	@Autowired
	FlowerRepository flowerRepository;

	@Autowired
	MonthRepository monthRepository;

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
		if (isNotNull(suggestion) && isNotNull(suggestion.getSpecies())) {
			Species newSpecies = suggestion.getSpecies();
			Fruit newFruit = newSpecies.getFruit();
			Flower newFlower = newSpecies.getFlower();
			if (isNotNull(newFruit)) {
				List<Month> MonthListDB = monthRepository.findAllByNumberIn(newFruit.getMonthNumbers());
				newFruit = new Fruit(newFruit.getFaunaConsumption(), newFruit.getHumanConsumption(), MonthListDB,
						newFruit.getDescription(), newFruit.getName());
				if (newFruit.isValid()) {
					newFruit = fruitRepository.save(newFruit);
				} else {
					return new ResponseEntity<List<String>>(newFruit.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
			}
			if (isNotNull(newFlower)) {
				List<Month> MonthListDB = monthRepository.findAllByNumberIn(newFlower.getMonthNumbers());
				newFlower = new Flower(newFlower.getIsAromatic(), newFlower.getDescription(), newFlower.getName(),
						newFlower.getSpecies(), newFlower.getColors(), MonthListDB);
				if (newFlower.isValid()) {
					newFlower = flowerRepository.save(newFlower);
				} else {
					return new ResponseEntity<List<String>>(newFlower.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
			}
			newSpecies = new Species(newSpecies.getAttractBirds(), newSpecies.getDescription(),
					newSpecies.getCultivationGuide(), newSpecies.getIsMedicinal(), newSpecies.getAttractBees(),
					newSpecies.getScientificName(), newSpecies.getCommonName(), newSpecies.getIsOrnamental(),
					newSpecies.getAverageHeight(), newSpecies.getGrowth(), newSpecies.getRootDepth(),
					newSpecies.getClimates(), newSpecies.getSoils(), newFlower, newFruit);
			if (newSpecies.isValid()) {
				Suggestion newSuggestion = new Suggestion(getCurrentUser(), newSpecies);
				if (newSuggestion.isValid()) {
					newSuggestion = suggestionRepository.save(newSuggestion);
					return new ResponseEntity<Suggestion>(newSuggestion, HttpStatus.OK);
				}
			}
			return new ResponseEntity<List<String>>(newSpecies.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Sugestão e/ou espécie não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByCurrentUser() {
		User currentUser = getCurrentUser();
		if (isNotNull(currentUser)) {
			Iterable<Suggestion> suggestionListDB = suggestionRepository.findByUser(getCurrentUser());
			return new ResponseEntity<Iterable<Suggestion>>(suggestionListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nenhum usuário logado.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Suggestion suggestionDB = suggestionRepository.findOne(id);
			if (isNotNull(suggestionDB)) {
				return new ResponseEntity<Suggestion>(suggestionDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Sugestão não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByPage(Integer page, Integer size) {
		if (isNotNull(page) && isNotNull(size) && is(size).smallerOrEqual(MAX_PAGE_SIZE)) {
			Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "lastModificationDate"));
			Iterable<Suggestion> suggestionListDB = suggestionRepository.findAll(pageable);
			return new ResponseEntity<Iterable<Suggestion>>(suggestionListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

}
