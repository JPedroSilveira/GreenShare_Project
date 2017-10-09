package com.seedshare.service.offer;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Offer;
import com.seedshare.service.BasicService;

/**
 * Service interface of Offer
 * @author joao.silva
 */
public interface OfferService extends BasicService<Offer,Long>{
	
	ResponseEntity<?> findAllByFlowerShop(Long id);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAllByUser(Long id);

	ResponseEntity<?> findAllBySpecies(Long id);

}
