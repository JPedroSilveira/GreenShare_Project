package com.seedshare.service.achievement;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.achievement.Achievement;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface AchievementService extends BasicService<Achievement, Long> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCategory(Short category);

	ResponseEntity<?> update(Achievement achievement);
}
