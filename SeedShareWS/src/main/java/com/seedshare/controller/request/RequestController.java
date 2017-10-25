package com.seedshare.controller.request;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.offer.Request;

/**
 * Controller interface of {@link com.seedshare.entity.offer.Request}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface RequestController extends BasicController<Request, Long>{

	ResponseEntity<?> findAllByOffer(Long id);

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> acceptRequest(Long id);
	
}
