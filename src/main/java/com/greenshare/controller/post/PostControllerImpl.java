package com.greenshare.controller.post;

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

import com.greenshare.entity.post.Post;
import com.greenshare.service.post.PostServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.post.Post}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/post/")
public class PostControllerImpl implements PostController {

	@Autowired
	PostServiceImpl postService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Post post) {
		return postService.save(post);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return postService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return postService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Post post) {
		return postService.update(post);
	}

	@Override
	@GetMapping("page/{page}/size/{size}")
	public ResponseEntity<?> findAllByPage(@PathVariable Integer page, @PathVariable Integer size) {
		return postService.findAllByPage(page, size);
	}

	@Override
	@GetMapping("user/{id}/page/{page}/size/{size}")
	public ResponseEntity<?> findAllByUser(@PathVariable Long id, @PathVariable Integer page,
			@PathVariable Integer size) {
		return postService.findAllByUser(id, page, size);
	}

	@Override
	@GetMapping("species/{id}/page/{page}/size/{size}")
	public ResponseEntity<?> findAllBySpecies(@PathVariable Long id, @PathVariable Integer page,
			@PathVariable Integer size) {
		return postService.findAllBySpecies(id, page, size);
	}

	@Override
	@GetMapping("state/{id}/page/{page}/size/{size}")
	public ResponseEntity<?> findAllByState(@PathVariable Long id, @PathVariable Integer page,
			@PathVariable Integer size) {
		return postService.findAllByState(id, page, size);
	}

	@Override
	@GetMapping("city/{id}/page/{page}/size/{size}")
	public ResponseEntity<?> findAllByCity(@PathVariable Long id, @PathVariable Integer page,
			@PathVariable Integer size) {
		return postService.findAllByCity(id, page, size);
	}

}
