package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Achievement;
import com.seedshare.entity.User;
import com.seedshare.entity.UserAchievement;

/**
 * Repository Interface for UserAchievement
 * 
 * @author joao.silva
 */
public interface UserAchievementRepository extends PagingAndSortingRepository<UserAchievement, Long> {
	
	Iterable<UserAchievement> findAllByUserAndAchievement(User user, Achievement achievement);

}