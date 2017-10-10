package com.seedshare.service.post;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Post;
import com.seedshare.service.BasicService;

/**
 * Service interface of Post
 * @author joao.silva
 */
public interface PostService extends BasicService<Post,Long>{

	ResponseEntity<?> findAllByPage(Integer page, Integer size);
	
}
