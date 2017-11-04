package com.greenshare.controller.achievement;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.achievement.Achievement;

/**
 * Controller interface for {@link com.greenshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 */
public interface AchievementController extends BasicController<Achievement, Long> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCategory(Short category);

	ResponseEntity<?> update(Achievement achievement);
}
