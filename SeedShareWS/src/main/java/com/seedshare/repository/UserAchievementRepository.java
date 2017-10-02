package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.UserAchievement;

/**
 * Repository Class for UserAchievement
 * @author joao.silva
 */
public interface UserAchievementRepository extends PagingAndSortingRepository<UserAchievement, Long>{

}