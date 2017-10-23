package com.seedshare.service.post_comment;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.post.PostComment;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.post.PostComment}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface PostCommentService extends BasicService<PostComment, Long>{
	
	ResponseEntity<?> findAllByPost(Long id);
	
}
