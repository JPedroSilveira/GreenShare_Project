package com.seedshare.service.offer_comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.OfferComment;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.OfferCommentRepository;

/**
 * Implementation of {@link com.seedshare.service.offer_comment.OfferCommentService} interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class OfferCommentServiceImpl extends IsHelper implements OfferCommentService{

	@Autowired
	OfferCommentRepository offerCommentRepository;
	
	@Override
	public ResponseEntity<?> save(OfferComment offerComment) {
		if(isNotNull(offerComment)) {
			OfferComment newOfferComment = new OfferComment(offerComment.getText(), getCurrentUser(), offerComment.getOffer());
			if(newOfferComment.isValid()) {
				newOfferComment = offerCommentRepository.save(newOfferComment);
				return new ResponseEntity<OfferComment>(newOfferComment, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newOfferComment.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("O comentário da oferta não pode ser nulo.", HttpStatus.BAD_REQUEST); 
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			offerCommentRepository.delete(id);
			return new ResponseEntity<String>("Comentário deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			OfferComment offerCommentDB = offerCommentRepository.findOne(id);
			if (isNotNull(offerCommentDB)) {
				return new ResponseEntity<OfferComment>(offerCommentDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Comentário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
