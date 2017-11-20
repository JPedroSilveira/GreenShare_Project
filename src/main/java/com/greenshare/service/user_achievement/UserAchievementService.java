package com.greenshare.service.user_achievement;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.achievement.UserAchievement;

/**
 * Service interface of {@link com.greenshare.entity.achievement.UserAchievement}
 * 
 * @author joao.silva
 */
public interface UserAchievementService {

	ResponseEntity<?> save(UserAchievement userAchievement);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findAllByUser(Long id);

}
