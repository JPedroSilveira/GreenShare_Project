package com.greenshare.service.post;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.post.Post;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.post.Post} entity
 * 
 * @author joao.silva
 */
public interface PostService extends BasicService<Post,Long>{

	ResponseEntity<?> findAllByPage(Integer page, Integer size);

	ResponseEntity<?> findAllByUser(Long id, Integer page, Integer size);

	ResponseEntity<?> findAllBySpecies(Long id, Integer page, Integer size);
	
	ResponseEntity<?> findAllByState(Long id, Integer page, Integer size);
	
	ResponseEntity<?> findAllByCity(Long id, Integer page, Integer size);
	
}
