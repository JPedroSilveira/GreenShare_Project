package com.seedshare.service.post;

import java.util.List;

import com.seedshare.entity.Post;
import com.seedshare.service.BasicService;

/**
 * Service interface of Post
 * @author joao.silva
 */
public interface PostService extends BasicService<Post,Long>{

	List<Post> findAll();
	
}
