package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Achievement;

/**
 * Repository Interface for Achievement
 * 
 * @author joao.silva
 */
public interface AchievementRepository extends PagingAndSortingRepository<Achievement, Long> {

	Iterable<Achievement> findAllByCategory(Short category);

}
