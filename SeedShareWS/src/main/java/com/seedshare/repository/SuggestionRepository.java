package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Suggestion;

/**
 * Repository Class for Suggestion
 * @author joao.silva
 */
public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long>{

}