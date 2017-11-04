package com.greenshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.post.Post;

/**
 * Repository Interface of {@link com.greenshare.entity.post.Post}
 * 
 * @author joao.silva
 */
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

	Page<Post> findAllByUser(Long id, Pageable pageable);

	Page<Post> findAllBySpecies(Long id, Pageable pageable);

	Page<Post> findAllByUserAddressCityState(Long id, Pageable pageable);

	Page<Post> findAllByUserAddressCity(Long id, Pageable pageable);

}
