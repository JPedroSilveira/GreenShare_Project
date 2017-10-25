package com.seedshare.service.user_achievement;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.achievement.UserAchievement;

/**
 * Service interface of {@link com.seedshare.entity.achievement.UserAchievement}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface UserAchievementService {

	ResponseEntity<?> save(UserAchievement userAchievement);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> findAllByUser(Long id);

}
