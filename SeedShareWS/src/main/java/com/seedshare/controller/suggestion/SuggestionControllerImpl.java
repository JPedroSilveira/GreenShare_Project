package com.seedshare.controller.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Suggestion;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.suggestion.SuggestionServiceImpl;

/**
 * Implementation of {@link com.seedshare.controller.suggestion.SuggestionController} interface
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/suggestion/")
public class SuggestionControllerImpl extends IsHelper implements SuggestionController {

	@Autowired
	SuggestionServiceImpl suggestionService;

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return suggestionService.delete(id);
	}

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Suggestion suggestion) {
		return suggestionService.save(suggestion);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(Long id) {
		return suggestionService.findOne(id);
	}

	@Override
	@GetMapping("page/{page}/size/{size}")
	public ResponseEntity<?> findAllByPage(@PathVariable Integer page, @PathVariable Integer size) {
		return suggestionService.findAllByPage(page, size);
	}

	@Override
	@GetMapping("current_user/")
	public ResponseEntity<?> findByCurrentUser() {
		return suggestionService.findByCurrentUser();
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> enable(Long id) {
		return suggestionService.enable(id);
	}

}
