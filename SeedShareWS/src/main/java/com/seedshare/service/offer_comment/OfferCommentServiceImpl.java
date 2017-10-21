package com.seedshare.service.offer_comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Offer;
import com.seedshare.entity.OfferComment;
import com.seedshare.entity.User;
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
				return new ResponseEntity<Offer>(newOfferComment, HttpStatus.OK);
			}
		}
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
