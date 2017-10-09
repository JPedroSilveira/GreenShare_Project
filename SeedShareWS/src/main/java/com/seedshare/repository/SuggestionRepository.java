package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Suggestion;
import com.seedshare.entity.User;

/**
 * Repository Interface for Suggestion
 * 
 * @author joao.silva
 */
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {
	Iterable<Suggestion> findByUser(User user);
}