package com.seedshare.controller.suggestion;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Suggestion;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.suggestion.SuggestionServiceImpl;

/**
 * Implementation of  SuggestionController interface
 * @author joao.silva
 */
@RestController
@RequestMapping("/suggestion")
public class SuggestionControllerImpl extends IsHelper implements SuggestionController{

	@Autowired
	SuggestionServiceImpl suggestionService;
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		if(suggestionService.delete(id)) {
			return new ResponseEntity<String>("Sugestão deletada com sucesso", HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Erro ao deletar sugestão", HttpStatus.BAD_REQUEST);   
	}

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody @Valid Suggestion suggestion) {
		Suggestion response = suggestionService.save(suggestion);
		if(isNotNull(response)) {
			return new ResponseEntity<String>("Sugestão salva com sucesso", HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Erro ao salvar sugestão", HttpStatus.BAD_REQUEST);   
	}

	@Override
	@GetMapping("/findByUser")
	public ResponseEntity<?> findByUser() {
		List<Suggestion> response = suggestionService.findByUser();
		if(response != null) {
			return new ResponseEntity<List<Suggestion>>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Erro ao procurar sugestões associadas ao usuário", HttpStatus.BAD_REQUEST);   
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(long id) {
		Suggestion response = suggestionService.findOne(id);
		if(response != null) {
			return new ResponseEntity<Suggestion>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Erro ao procurar sugestão", HttpStatus.BAD_REQUEST);   
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		List<Suggestion> response = suggestionService.findAll();
		if(response != null) {
			return new ResponseEntity<List<Suggestion>>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Erro ao procurar sugestões associadas ao usuário", HttpStatus.BAD_REQUEST);   
	}
	
}
