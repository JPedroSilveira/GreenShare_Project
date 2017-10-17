package com.seedshare.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Post;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.PostRepository;

/**
 * Implementation of {@link com.seedshare.service.post.PostService} interface
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@Service
public class PostServiceImpl extends IsHelper implements PostService {

	@Autowired
	PostRepository postRepository;
	
	private static final int MAX_PAGE_SIZE = 100; 

	@Override
	public ResponseEntity<?> save(Post post) {
		if (isNotNull(post)) {
			Post newPost = new Post(getCurrentUser(), post.getSpecies(), post.getText());
			if (newPost.isValid()) {
				newPost = postRepository.save(newPost);
				newPost.getUser().clearPrivateData();
				return new ResponseEntity<Post>(newPost, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newPost.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Postagem não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			Post postDB = postRepository.findOne(id);
			if (isNotNull(postDB) && postDB.getUser().getId() == getCurrentUser().getId()) {
				postRepository.delete(id);
				return new ResponseEntity<String>("Postagem deletada.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Postagem não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Post postDB = postRepository.findOne(id);
			if (isNotNull(postDB)) {
				postDB.getUser().clearPrivateData();
				return new ResponseEntity<Post>(postDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByPage(Integer page, Integer size) {
		if (isValidPage(page, size)) {
			Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "lastModificationDate"));
			Page<Post> postListDB = postRepository.findAll(pageable);
			postListDB.forEach(post -> post.getUser().clearPrivateData());
			return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByUser(Integer page, Integer size, Long id) {
		if (isValidPage(page, size)) {
			if (isNotNull(id)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "lastModificationDate"));
				Page<Post> postListDB = postRepository.findAllByUser(id, pageable);
				postListDB.forEach(post -> post.getUser().clearPrivateData());
				return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllBySpecies(Integer page, Integer size, Long id) {
		if (isValidPage(page, size)) {
			if (isNotNull(id)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "lastModificationDate"));
				Page<Post> postListDB = postRepository.findAllBySpecies(id, pageable);
				postListDB.forEach(post -> post.getUser().clearPrivateData());
				return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

	private boolean isValidPage(Integer page, Integer size) {
		if (isNotNull(page) && isNotNull(size) && is(size).smallerOrEqual(MAX_PAGE_SIZE)) {
			return true;
		}
		return false;
	}
}
