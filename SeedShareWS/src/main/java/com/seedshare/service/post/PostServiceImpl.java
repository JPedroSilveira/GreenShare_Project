package com.seedshare.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Post;
import com.seedshare.repository.PostRepository;

/**
 * Implementation of Post Service interface
 * @author joao.silva
 */
@Service
public class PostServiceImpl implements PostService{

	@Autowired
    PostRepository postRepository;
	
	@Override
	public Post save(Post post) {
		if(post.isValid()) {
			return postRepository.save(post);
		}
		return null;
	}

	@Override
	public void delete(Post post) {
		postRepository.delete(post);
	}

	@Override
	public Post findOne(Long id) {
		return postRepository.findOne(id);
	}

}
