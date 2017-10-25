package com.seedshare.controller.achievement;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.achievement.Achievement;

/**
 * Controller interface for {@link com.seedshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 */
public interface AchievementController extends BasicController<Achievement, Long> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCategory(Short category);

	ResponseEntity<?> update(Achievement achievement);
}
