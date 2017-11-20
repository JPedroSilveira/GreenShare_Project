package com.greenshare.controller.post_comment;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.post.PostComment;

/**
 * Controller interface of {@link com.greenshare.entity.post.PostComment}
 * 
 * @author joao.silva
 */
public interface PostCommentController extends BasicController<PostComment, Long>{
	
	ResponseEntity<?> findAllByPost(Long id);

}
