package com.seedshare.controller.achievement;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.Achievement;
import com.seedshare.service.achievement.AchievementService;

/**
 * Controller class for Achievement
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/achivement")
public class AchievementControllerImpl implements AchievementController {

	@Autowired
	AchievementService achievementService;

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody @Valid Achievement achievement) {
		return achievementService.save(achievement);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return achievementService.delete(id);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return achievementService.findOne(id);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return achievementService.findAll();
	}

}
