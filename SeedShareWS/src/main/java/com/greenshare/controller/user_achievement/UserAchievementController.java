package com.greenshare.controller.user_achievement;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.achievement.UserAchievement;

/**
 * Controller interface of {@link com.greenshare.entity.achievement.UserAchievement}
 * 
 * @author joao.silva
 */
public interface UserAchievementController{

	ResponseEntity<?> save(UserAchievement userAchievement);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findAllByUser(Long id);
	
}
