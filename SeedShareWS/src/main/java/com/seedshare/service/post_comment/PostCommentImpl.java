package com.seedshare.service.post_comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.PostComment;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.PostCommentRepository;

/**
 * Implementation of {@link com.seedshare.service.post_comment.PostCommentService} interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class PostCommentImpl extends IsHelper implements PostCommentService{

	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Override
	public ResponseEntity<?> save(PostComment postComment) {
		if(isNotNull(postComment)) {
			PostComment newPostComment = new PostComment(postComment.getText(), getCurrentUser(), postComment.getPost());
			if(newPostComment.isValid()) {
				newPostComment = postCommentRepository.save(newPostComment);
				return new ResponseEntity<PostComment>(newPostComment, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newPostComment.getValidationErrors(), HttpStatus.BAD_REQUEST);
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

}
