package com.seedshare.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Suggestion;
import com.seedshare.entity.User;

/**
 * Repository Class for Suggestion
 * @author joao.silva
 */
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long>{
	List<Suggestion> findByUser(User user);
}