package com.seedshare.controller.offer;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.offer.Offer;

/**
 * Controller interface of {@link com.seedshare.entity.offer.Offer}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface OfferController extends BasicController<Offer, Long>{
	
	ResponseEntity<?> findAllByFlowerShop(Long id);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAllByUser(Long id);

	ResponseEntity<?> findAllBySpecies(Long id);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);
	
	ResponseEntity<?> findAllByCurrentUser();
	
}
