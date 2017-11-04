package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenshare.entity.achievement.Achievement;
import com.greenshare.entity.achievement.UserAchievement;
import com.greenshare.entity.user.User;

/**
 * Repository Interface of {@link com.greenshare.entity.achievement.UserAchievement}
 * 
 * @author joao.silva
 */
public interface UserAchievementRepository extends PagingAndSortingRepository<UserAchievement, Long> {
	
	Iterable<UserAchievement> findAllByUserAndAchievement(User user, Achievement achievement);

	Iterable<UserAchievement> findAllByUser(Long id);

}