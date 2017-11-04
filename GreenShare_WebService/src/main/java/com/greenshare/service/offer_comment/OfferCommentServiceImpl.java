package com.greenshare.service.offer_comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.offer.OfferComment;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.OfferCommentRepository;
import com.greenshare.repository.OfferRepository;

/**
 * Implementation of
 * {@link com.greenshare.service.offer_comment.OfferCommentService} interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class OfferCommentServiceImpl extends IsHelper implements OfferCommentService {

	@Autowired
	OfferCommentRepository offerCommentRepository;

	@Autowired
	OfferRepository offerRepository;

	@Override
	public ResponseEntity<?> save(OfferComment offerComment) {
		if (isNotNull(offerComment) && isNotNull(offerComment.getOffer())
				&& isNotNull(offerComment.getOffer().getId())) {
			Offer offerDB = offerRepository.findOne(offerComment.getOffer().getId());
			if (isNotNull(offerDB)) {
				OfferComment newOfferComment = new OfferComment(offerComment.getText(), getCurrentUser(), offerDB);
				if (newOfferComment.isValid()) {
					newOfferComment = offerCommentRepository.save(newOfferComment);
					return new ResponseEntity<OfferComment>(newOfferComment, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(newOfferComment.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Oferta do comentário não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("O comentário e a oferta não podem ser nulos.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			OfferComment offerCommentDB = offerCommentRepository.findOne(id);
			if (isNotNull(offerCommentDB)) {
				if (offerCommentDB.getUser().getId() != getCurrentUserId()) {
					offerCommentRepository.delete(id);
					return new ResponseEntity<String>("Comentário deletado.", HttpStatus.OK);
				}
				return new ResponseEntity<String>("Usuário atual não possui permissão para deletar este comentário.",
						HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Comentário não encontrado.", HttpStatus.NOT_FOUND);
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

	@Override
	public ResponseEntity<?> update(OfferComment offerComment) {
		if (isNotNull(offerComment)) {
			OfferComment offerCommentDB = offerCommentRepository.findOne(offerComment.getId());
			if (isNotNull(offerCommentDB)) {
				if (offerCommentDB.getUser().getId() == getCurrentUserId()) {
					offerCommentDB.update(offerComment);
					if (offerCommentDB.isValid()) {
						offerCommentDB = offerCommentRepository.save(offerCommentDB);
						return new ResponseEntity<OfferComment>(offerCommentDB, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(offerCommentDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Comentário não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Comentário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Comentário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
