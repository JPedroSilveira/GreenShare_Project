package com.seedshare.service.achievement;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Achievement;
import com.seedshare.service.BasicService;

/**
 * Service interface of Achievement
 * 
 * @author joao.silva
 */
public interface AchievementService extends BasicService<Achievement,Long>{
	ResponseEntity<?> findAll();
}
