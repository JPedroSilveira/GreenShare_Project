package com.seedshare.service.offer;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.offer.Offer;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.offer.Offer}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface OfferService extends BasicService<Offer, Long> {

	ResponseEntity<?> findAllByFlowerShop(Long id);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAllByUser(Long id);

	ResponseEntity<?> findAllBySpecies(Long id);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);
	
	ResponseEntity<?> findAllByCurrentUser();
	
}
