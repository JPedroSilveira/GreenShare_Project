package com.seedshare.service.request;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Request;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Request}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface RequestService extends BasicService<Request, Long> {

	ResponseEntity<?> findAllByOffer(Long id);

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> acceptRequest(Long id);

}
