package com.greenshare.controller.request;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.offer.Request;

/**
 * Controller interface of {@link com.greenshare.entity.offer.Request}
 * 
 * @author joao.silva
 */
public interface RequestController extends BasicController<Request, Long>{

	ResponseEntity<?> findAllByOffer(Long id);

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> acceptRequest(Long id);
	
}
