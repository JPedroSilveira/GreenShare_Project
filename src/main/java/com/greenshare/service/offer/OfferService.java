package com.greenshare.service.offer;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.vegetable.SearchClass;
import com.greenshare.service.BasicService;

/**
 * Service interface of {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 */
public interface OfferService extends BasicService<Offer, Long> {

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
