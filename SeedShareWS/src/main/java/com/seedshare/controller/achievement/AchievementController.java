package com.seedshare.controller.achievement;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.Achievement;

/**
 * Controller interface for Achievement
 * 
 * @author joao.silva
 */
public interface AchievementController extends BasicController<Achievement, Long> {
	ResponseEntity<?> findAll();
}
