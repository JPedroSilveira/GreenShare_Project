package com.greenshare.service.request;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.offer.Request;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.offer.Request}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface RequestService extends BasicService<Request, Long> {

	ResponseEntity<?> findAllByOffer(Long id);

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> acceptRequest(Long id);

}
