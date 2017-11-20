package com.greenshare.controller.offer_comment;

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

import com.greenshare.entity.offer.OfferComment;
import com.greenshare.service.offer_comment.OfferCommentServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.offer.OfferComment}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/offer/comment/")
public class OfferCommentControllerImpl implements OfferCommentController{

	@Autowired
	OfferCommentServiceImpl offerCommentService;
	
	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody OfferComment offerComment) {
		return offerCommentService.save(offerComment);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return offerCommentService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return offerCommentService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody OfferComment offerComment) {
		return offerCommentService.update(offerComment);
	}

}
