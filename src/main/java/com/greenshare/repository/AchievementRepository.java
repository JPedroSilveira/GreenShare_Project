package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.achievement.Achievement;

/**
 * Repository Interface for {@link com.greenshare.entity.achievement.Achievement}
 * 
 * @author joao.silva
 */
@Repository
public interface AchievementRepository extends PagingAndSortingRepository<Achievement, Long> {

	Iterable<Achievement> findAllByCategoryOrderByRequiredScoreAsc(Short category);

	Iterable<Achievement> findAllByOrderByRequiredScoreAsc();

}
