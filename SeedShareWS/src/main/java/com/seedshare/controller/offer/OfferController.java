package com.seedshare.controller.offer;

import org.springframework.http.ResponseEntity;

import com.seedshare.controller.BasicController;
import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.offer.Offer;
import com.seedshare.entity.user.User;
import com.seedshare.entity.vegetable.Species;

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
	
}
