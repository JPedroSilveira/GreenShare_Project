package com.greenshare.service.post_comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.post.Post;
import com.greenshare.entity.post.PostComment;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.PostCommentRepository;
import com.greenshare.repository.PostRepository;

/**
 * Implementation of
 * {@link com.greenshare.service.post_comment.PostCommentService} interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class PostCommentServiceImpl extends IsHelper implements PostCommentService {

	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public ResponseEntity<?> save(PostComment postComment) {
		if (isNotNull(postComment)) {
			Post post = postComment.getPost();
			if(isNotNull(post)) {
				post = postRepository.findOne(post.getId());
				if(isNotNull(post)) {
					PostComment newPostComment = new PostComment(postComment.getText(), getCurrentUser(),
							post);
					if (newPostComment.isValid()) {
						newPostComment = postCommentRepository.save(newPostComment);
						return new ResponseEntity<PostComment>(newPostComment, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(newPostComment.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("A postagem não pode ser nula.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("O comentário da oferta não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			postCommentRepository.delete(id);
			return new ResponseEntity<String>("Comentário deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			PostComment postCommentDB = postCommentRepository.findOne(id);
			if (isNotNull(postCommentDB)) {
				return new ResponseEntity<PostComment>(postCommentDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Comentário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(PostComment postComment) {
		if (isNotNull(postComment)) {
			PostComment postCommentDB = postCommentRepository.findOne(postComment.getId());
			if (isNotNull(postCommentDB)) {
				if (postCommentDB.getUser().getId() == getCurrentUserId()) {
					postCommentDB.update(postComment);
					if (postCommentDB.isValid()) {
						postCommentDB = postCommentRepository.save(postCommentDB);
						return new ResponseEntity<PostComment>(postCommentDB, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(postCommentDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Comentário não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Comentário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Comentário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByPost(Long id) {
		if(isNotNull(id)) {
			Post postDB = postRepository.findOne(id);
			if(isNotNull(postDB)) {
				Iterable<PostComment> postCommentList = postCommentRepository.findAllByPost(postDB.getId());
				return new ResponseEntity<Iterable<PostComment>>(postCommentList, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
