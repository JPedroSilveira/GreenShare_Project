package com.seedshare.service.post;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.post.Post;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.post.Post} entity
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface PostService extends BasicService<Post,Long>{

	ResponseEntity<?> findAllByPage(Integer page, Integer size);

	ResponseEntity<?> findAllByUser(Long id, Integer page, Integer size);

	ResponseEntity<?> findAllBySpecies(Long id, Integer page, Integer size);
	
	ResponseEntity<?> findAllByState(Long id, Integer page, Integer size);
	
	ResponseEntity<?> findAllByCity(Long id, Integer page, Integer size);
	
}
