package com.seedshare.controller.post_comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.entity.post.PostComment;
import com.seedshare.service.post_comment.PostCommentServiceImpl;

/**
 * Controller class of {@link com.seedshare.entity.post.PostComment}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@RestController
@RequestMapping("/post/comment/")
public class PostCommentControllerImpl implements PostCommentController{

	@Autowired
	PostCommentServiceImpl postCommentService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody PostComment postComment) {
		return postCommentService.save(postComment);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return postCommentService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return postCommentService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody PostComment postComment) {
		return postCommentService.update(postComment);
	}

	@Override
	@GetMapping("post/{id}")
	public ResponseEntity<?> findAllByPost(@PathVariable Long id) {
		return postCommentService.findAllByPost(id);
	}
	
	
}
