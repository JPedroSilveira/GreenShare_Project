package com.greenshare.service.post_comment;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.post.PostComment;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.post.PostComment}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface PostCommentService extends BasicService<PostComment, Long>{
	
	ResponseEntity<?> findAllByPost(Long id);
	
}
