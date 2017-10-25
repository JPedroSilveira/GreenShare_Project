package com.seedshare.controller.post_comment;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.post.PostComment;

/**
 * Controller interface of {@link com.seedshare.entity.post.PostComment}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface PostCommentController extends BasicController<PostComment, Long>{
	
	ResponseEntity<?> findAllByPost(Long id);

}
