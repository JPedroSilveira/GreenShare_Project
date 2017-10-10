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
 * Implementation of Post Service interface
 * @author joao.silva
 */
@Service
public class PostServiceImpl extends IsHelper implements PostService{

	@Autowired
    PostRepository postRepository;
	
	@Override
	public ResponseEntity<?> save(Post post) {
		if(isNotNull(post)) {
			Post newPost = new Post(getCurrentUser(), post.getSpecies(), post.getText());
			if(newPost.isValid()) {
				newPost = postRepository.save(newPost);
				newPost.getUser().cleanPrivateDate();
				return new ResponseEntity<Post>(newPost, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newPost.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Postagem não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			postRepository.delete(id);
			return new ResponseEntity<String>("Nível de crescimento deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if(isNotNull(id)) {
			Post postDB = postRepository.findOne(id);
			if(isNotNull(postDB)) {
				postDB.getUser().cleanPrivateDate();
				return new ResponseEntity<Post>(postDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
    public ResponseEntity<?> findAllByPage(Integer page, Integer size) {
		if(isNotNull(page) && isNotNull(size)) {
			if(is(size).smallerThan(100)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "lastModificationDate"));
		        Page<Post> postListDB = postRepository.findAll(pageable);
				return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);	
			}
			return new ResponseEntity<String>("Tamanho não pode ser maior que cem.", HttpStatus.BAD_REQUEST);			
		}
		return new ResponseEntity<String>("Página e tamanho não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
}
