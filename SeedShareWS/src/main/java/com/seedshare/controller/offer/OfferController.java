package com.seedshare.controller.offer;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;

/**
 * @author joao.silva
 */
public interface OfferController extends BasicController<Offer, Long>{
	
	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findAllByUser(Long id);

	ResponseEntity<?> findAllByUser(User user);

	ResponseEntity<?> findAllByFlowerShop(FlowerShop flowerShop);

	ResponseEntity<?> findAllByFlowerShop(Long id);

	ResponseEntity<?> findAllBySpecies(Species species);

	ResponseEntity<?> findAllBySpecies(Long id);

	ResponseEntity<?> delete(Offer offer);
}
