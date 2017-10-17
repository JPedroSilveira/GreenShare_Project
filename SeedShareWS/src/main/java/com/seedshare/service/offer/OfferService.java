package com.seedshare.service.offer;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Offer;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Offer}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface OfferService extends BasicService<Offer, Long> {

	ResponseEntity<?> findAllByFlowerShop(Long id);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAllByUser(Long id);

	ResponseEntity<?> findAllBySpecies(Long id);

	ResponseEntity<?> findAllByState(Long id);

	ResponseEntity<?> findAllByCity(Long id);

	ResponseEntity<?> addComment(Long id, String text);

	ResponseEntity<?> deleteComment(Long id);
}
