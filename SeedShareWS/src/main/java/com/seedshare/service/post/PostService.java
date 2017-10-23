package com.seedshare.service.post;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.post.Post;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.post.Post} entity
 * 
 * @author joao.silva
 */
public interface PostService extends BasicService<Post,Long>{

	ResponseEntity<?> findAllByPage(Integer page, Integer size);

	ResponseEntity<?> findAllByUser(Integer page, Integer size, Long id);

	ResponseEntity<?> findAllBySpecies(Integer page, Integer size, Long id);
	
	ResponseEntity<?> findAllByState(Integer page, Integer size, Long id);
	
	ResponseEntity<?> findAllByCity(Integer page, Integer size, Long id);

	ResponseEntity<?> update(Post post);
	
}
