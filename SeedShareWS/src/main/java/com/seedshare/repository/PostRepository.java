package com.seedshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Post;

/**
 * Repository Interface for Post
 * 
 * @author joao.silva
 */
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
	Page<Post> findAllByUser(Long id, Pageable pageable);
	
	Page<Post> findAllBySpecies(Long id, Pageable pageable);
}
