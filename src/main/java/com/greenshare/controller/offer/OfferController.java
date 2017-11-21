package com.greenshare.controller.offer;

import org.springframework.http.ResponseEntity;

import com.greenshare.controller.BasicController;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.vegetable.SearchClass;

/**
 * Controller interface of {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 */
public interface OfferController extends BasicController<Offer, Long>{
	
	ResponseEntity<?> findAllByFlowerShop(Long id);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAllByUser(Long id);

	ResponseEntity<?> findAllBySpecies(Long id);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);
	
	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> findAll();

	ResponseEntity<?> search(Integer page, Integer size, SearchClass searchClass);
	
}
