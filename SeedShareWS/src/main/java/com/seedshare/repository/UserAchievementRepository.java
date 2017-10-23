package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.achievement.Achievement;
import com.seedshare.entity.achievement.UserAchievement;
import com.seedshare.entity.user.User;

/**
 * Repository Interface of {@link com.seedshare.entity.achievement.UserAchievement}
 * 
 * @author joao.silva
 */
public interface UserAchievementRepository extends PagingAndSortingRepository<UserAchievement, Long> {
	
	Iterable<UserAchievement> findAllByUserAndAchievement(User user, Achievement achievement);

}