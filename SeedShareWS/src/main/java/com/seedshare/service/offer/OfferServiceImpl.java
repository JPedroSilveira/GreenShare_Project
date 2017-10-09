package com.seedshare.service.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;
import com.seedshare.enumeration.OfferStatus;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.OfferRepository;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of Offer Service interface
 * @author joao.silva
 */
@Service
public class OfferServiceImpl extends IsHelper implements OfferService{

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	FlowerShopRepository flowerShopRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<?> save(Offer offer) {
		Offer newOffer = new Offer(offer.getUnitPrice(), offer.getAmount(), getCurrentUser(), offer.getSpecies(),offer.getDescription());
		if(newOffer.isValid()) {
			Offer response = offerRepository.save(offer);
			if(response != null) {
				response.getUser().cleanPrivateDate();
			}
		}
		return null;
	}
	
	@Override
	public ResponseEntity<?> delete(Long id) {
		Offer offerToDelete = offerRepository.findOne(id);
		deleteOffer(offerToDelete);
	}
	
	private ResponseEntity<?> deleteOffer(Offer offerToDelete) {
		if(offerToDelete.getUser().getId() == getCurrentUser().getId()) {
			offerToDelete.setOfferStatus(OfferStatus.Closed);
			offerRepository.save(offerToDelete);
		}
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		Offer response = offerRepository.findOne(id);
		if(response != null) {
			response.getUser().cleanPrivateDate();
			return response;
		}
		return null;
	}
	
	@Override
	public ResponseEntity<?> findAllByUser(Long id) {
		if(isNotNull(id)) {
			User user = userRepository.findOne(id);
			if(user != null) {
				List<Offer> response = offerRepository.findAllByUser(user);
				response.forEach(offer -> offer.getUser().cleanPrivateDate());
				return response;
			}
		}
		return null;
	}
	
	@Override
	public ResponseEntity<?> findAllByFlowerShop(Long id) {
		if(isNotNull(id)) {
			FlowerShop flowerShopDB = flowerShopRepository.findOne(id);
			if(isNotNull(flowerShopDB)) {
				List<Offer> response = offerRepository.findAllByUser(flowerShopDB.getUser());
				response.forEach(offer -> offer.getUser().cleanPrivateDate());
				return response;
			}
		}
		return null;
	}
	
	@Override
	public ResponseEntity<?> findAllBySpecies(Long id) {
		if(isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			if(isNotNull(speciesDB)) {
				List<Offer> response = offerRepository.findAllBySpecies(speciesDB);
				response.forEach(offer -> offer.getUser().cleanPrivateDate());
				return response;
			}
		}
		return null;
	}
}
