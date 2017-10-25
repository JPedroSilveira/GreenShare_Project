package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.achievement.Achievement;

/**
 * Repository Interface for {@link com.seedshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 */
public interface AchievementRepository extends PagingAndSortingRepository<Achievement, Long> {

	Iterable<Achievement> findAllByCategoryOrderByRequiredScoreAsc(Short category);

	Iterable<Achievement> findAllByOrderByRequiredScoreAsc();

}
