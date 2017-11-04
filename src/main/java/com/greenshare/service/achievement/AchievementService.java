package com.greenshare.service.achievement;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.achievement.Achievement;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface AchievementService extends BasicService<Achievement, Long> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCategory(Short category);

	ResponseEntity<?> update(Achievement achievement);
}
