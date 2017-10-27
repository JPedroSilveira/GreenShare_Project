package com.seedshare.controller.user_achievement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.achievement.UserAchievement;
import com.seedshare.service.user_achievement.UserAchievementServiceImpl;

/**
 * Controller class of {@link com.seedshare.entity.achievement.UserAchievement}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/user_achievement/")
public class UserAchievementControllerImpl implements UserAchievementController{

	@Autowired
	UserAchievementServiceImpl userAchievementService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody UserAchievement userAchievement) {
		return userAchievementService.save(userAchievement);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return userAchievementService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return userAchievementService.findOne(id);
	}

	@Override
	@GetMapping("user/{id}")
	public ResponseEntity<?> findAllByUser(@PathVariable Long id) {
		return userAchievementService.findAllByUser(id);
	}
	
}
