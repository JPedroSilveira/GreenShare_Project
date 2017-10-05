package com.seedshare.service.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.UserUtils;
import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;
import com.seedshare.enumeration.OfferStatus;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.OfferRepository;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of Offer Service interface
 * @author joao.silva
 */
@Service
public class OfferServiceImpl extends UserUtils implements OfferService{

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	FlowerShopRepository flowerShopRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Offer save(Offer offer) {
		Offer newOffer = new Offer(offer.getUnitPrice(), offer.getAmount(), getCurrentUser(), offer.getSpecies(),offer.getDescription());
		if(newOffer.isValid()) {
			return offerRepository.save(offer);
		}
		return null;
	}

	@Override
	public void delete(Offer offer) {
		Offer offerToDelete = offerRepository.findOne(offer.getId());
		deleteOffer(offerToDelete);
	}
	
	@Override
	public void delete(Long id) {
		Offer offerToDelete = offerRepository.findOne(id);
		deleteOffer(offerToDelete);
	}
	
	private void deleteOffer(Offer offerToDelete) {
		if(offerToDelete.getUser().getId() == getCurrentUser().getId()) {
			offerToDelete.setOfferStatus(OfferStatus.Closed);
			offerRepository.save(offerToDelete);
		}
	}

	@Override
	public Offer findOne(Long id) {
		return offerRepository.findOne(id);
	}
	
	@Override
	public List<Offer> findAllByUser(User user) {
		User response = userRepository.findOne(user.getId());
		if(response != null) {
			return offerRepository.findAllByUser(user);
		}
		return null;
	}
	
	@Override
	public List<Offer> findAllByUser(Long id) {
		User response = userRepository.findOne(id);
		if(response != null) {
			return offerRepository.findAllByUser(response);
		}
		return null;
	}
	
	@Override
	public List<Offer> findAllByFlowerShop(FlowerShop flowerShop) {
		FlowerShop flowerShopDB = flowerShopRepository.findOne(flowerShop.getId());
		return findAllByFlowerShopUser(flowerShopDB);
	}
	
	@Override
	public List<Offer> findAllByFlowerShop(Long id) {
		FlowerShop flowerShopDB = flowerShopRepository.findOne(id);
		return findAllByFlowerShopUser(flowerShopDB);
	}
	
	private List<Offer> findAllByFlowerShopUser(FlowerShop flowerShop){
		if(flowerShop != null && flowerShop.getUser() != null) {
			return offerRepository.findAllByUser(flowerShop.getUser());
		}
		return null;
	}
	
	@Override
	public List<Offer> findAllBySpecies(Species species) {
		Species speciesDB = speciesRepository.findOne(species.getId());
		return findAllBySpeciesResponse(speciesDB);
	}
	
	@Override
	public List<Offer> findAllBySpecies(Long id) {
		Species speciesDB = speciesRepository.findOne(id);
		return findAllBySpeciesResponse(speciesDB);
	}
	
	private List<Offer> findAllBySpeciesResponse(Species species){
		if(species != null) {
			return offerRepository.findAllBySpecies(species);
		}
		return null;
	}

}
