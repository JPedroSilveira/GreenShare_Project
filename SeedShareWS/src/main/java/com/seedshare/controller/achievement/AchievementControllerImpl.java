package com.seedshare.controller.achievement;

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

import com.seedshare.entity.achievement.Achievement;
import com.seedshare.service.achievement.AchievementService;

/**
 * Controller class for {@link com.seedshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/achivement/")
public class AchievementControllerImpl implements AchievementController {

	@Autowired
	AchievementService achievementService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Achievement achievement) {
		return achievementService.save(achievement);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return achievementService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return achievementService.findOne(id);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return achievementService.findAll();
	}

	@Override
	@GetMapping("/category/{category}")
	public ResponseEntity<?> findAllByCategory(@PathVariable Short category) {
		return achievementService.findAllByCategory(category);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Achievement achievement) {
		return achievementService.update(achievement);
	}

}
