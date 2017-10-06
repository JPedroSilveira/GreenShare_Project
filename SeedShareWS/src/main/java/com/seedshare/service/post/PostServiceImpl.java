package com.seedshare.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Post;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.PostRepository;

/**
 * Implementation of Post Service interface
 * @author joao.silva
 */
@Service
public class PostServiceImpl extends IsHelper implements PostService{

	@Autowired
    PostRepository postRepository;
	
	@Override
	public Post save(Post post) {
		if(isNotNull(post)) {
			Post newPost = new Post(getCurrentUser(), post.getSpecies(), post.getText());
			if(newPost.isValid()) {
				newPost = postRepository.save(newPost);
				newPost.getUser().cleanPrivateDate();
				return newPost;
			}
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		if(isNotNull(id)) {
			postRepository.delete(id);
		}
	}

	@Override
	public Post findOne(Long id) {
		if(isNotNull(id)) {
			Post response = postRepository.findOne(id);
			if(isNotNull(response)) {
				response.getUser().cleanPrivateDate();
				return response;
			}
		}
		return null;
	}
	
	@Override
	public List<Post> findAll() {
		List<Post> response = (List<Post>) postRepository.findAll();
		response.forEach(post -> post.getUser().cleanPrivateDate());
		return response;
	}
}
