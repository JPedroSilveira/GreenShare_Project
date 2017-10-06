package com.seedshare.service.offer;

import java.util.List;

import com.seedshare.entity.Offer;
import com.seedshare.service.BasicService;

/**
 * Service interface of Offer
 * @author joao.silva
 */
public interface OfferService extends BasicService<Offer,Long>{
	
	List<Offer> findAllByFlowerShop(Long id);

	void delete(Long id);

	List<Offer> findAllByUser(Long id);

	List<Offer> findAllBySpecies(Long id);

}
