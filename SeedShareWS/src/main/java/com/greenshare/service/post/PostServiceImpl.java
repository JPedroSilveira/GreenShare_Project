package com.greenshare.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.post.Post;
import com.greenshare.entity.vegetable.Species;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.PostRepository;
import com.greenshare.repository.SpeciesRepository;

/**
 * Implementation of {@link com.greenshare.service.post.PostService} interface
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@Service
public class PostServiceImpl extends IsHelper implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;

	private static final int MAX_PAGE_SIZE = 100;

	@Override
	public ResponseEntity<?> save(Post post) {
		if (isNotNull(post)) {
			Species species = post.getSpecies();
			if(isNotNull(species)) {
				species = speciesRepository.findOne(species.getId());
				if(isNull(species)) {
					return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.BAD_REQUEST);
				}
			}
			Post newPost = new Post(getCurrentUser(), species, post.getText());
			if (newPost.isValid()) {
				newPost = postRepository.save(newPost);
				return new ResponseEntity<Post>(newPost, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newPost.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Postagem não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Post post) {
		if (isNotNull(post)) {
			Post postDB = postRepository.findOne(post.getId());
			if (isNotNull(postDB)) {
				if (postDB.getUser().getId() == getCurrentUserId()) {
					postDB.update(post);
					if (postDB.isValid()) {
						postDB = postRepository.save(postDB);
						return new ResponseEntity<Post>(postDB, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(postDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Postagem não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Postagem atualizada não pode ser nula.", HttpStatus.BAD_REQUEST);
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
				return new ResponseEntity<Post>(postDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByPage(Integer page, Integer size) {
		if (isValidPage(page, size)) {
			Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "insertionDate"));
			Page<Post> postListDB = postRepository.findAll(pageable);
			return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByUser(Long id, Integer page, Integer size) {
		if (isValidPage(page, size)) {
			if (isNotNull(id)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "insertionDate"));
				Page<Post> postListDB = postRepository.findAllByUser(id, pageable);
				return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllBySpecies(Long id, Integer page, Integer size) {
		if (isValidPage(page, size)) {
			if (isNotNull(id)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "insertionDate"));
				Page<Post> postListDB = postRepository.findAllBySpecies(id, pageable);
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

	@Override
	public ResponseEntity<?> findAllByState(Long id, Integer page, Integer size) {
		if (isValidPage(page, size)) {
			if (isNotNull(id)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "insertionDate"));
				Page<Post> postListDB = postRepository.findAllByUserAddressCityState(id, pageable);
				return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCity(Long id, Integer page, Integer size) {
		if (isValidPage(page, size)) {
			if (isNotNull(id)) {
				Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "insertionDate"));
				Page<Post> postListDB = postRepository.findAllByUserAddressCity(id, pageable);
				return new ResponseEntity<Page<Post>>(postListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Paginação inválida.", HttpStatus.BAD_REQUEST);
	}
}
