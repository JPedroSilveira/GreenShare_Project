package com.seedshare.service.achievement;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Achievement;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Achievement}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface AchievementService extends BasicService<Achievement, Long> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> findAllByCategory(Short category);

}
