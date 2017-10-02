package com.seedshare.service.offer;

import java.util.List;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;
import com.seedshare.service.BasicService;

/**
 * Service interface of Offer
 * @author joao.silva
 */
public interface OfferService extends BasicService<Offer,Long>{

	List<Offer> findAllByUser(User user);

	List<Offer> findAllByFlowerShop(FlowerShop flowerShop);
	
	List<Offer> findAllByFlowerShop(Long id);

	List<Offer> findAllBySpecies(Species species);

	void delete(Long id);

	List<Offer> findAllByUser(Long id);

	List<Offer> findAllBySpecies(Long id);

}
