package com.seedshare.repository;

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

}
