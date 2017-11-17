package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.Suggestion;
import com.greenshare.entity.user.User;

/**
 * Repository Interface of {@link com.greenshare.entity.Suggestion}
 * 
 * @author joao.silva
 */
@Repository
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {
	
	Iterable<Suggestion> findByUser(User user);
	
}