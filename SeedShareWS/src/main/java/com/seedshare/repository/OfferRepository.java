package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.offer.Offer;

/**
 * Repository Interface of {@link com.seedshare.entity.offer.Offer}
 * 
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	Iterable<Offer> findAllByUserAndOfferStatus(Long id, Integer offerStatus);
	
	Iterable<Offer> findAllBySpeciesAndOfferStatus(Long id, Integer offerStatus);

	Iterable<Offer> findAllByFlowerShopAndOfferStatus(Long id, Integer offerStatus);

	Iterable<Offer> findAllByAddressCityStateAndOfferStatus(Long id, Integer offerStatus);

	Iterable<Offer> findAllByAddressCityAndOfferStatus(Long id, Integer offerStatus);
	
	Iterable<Offer> findAllByUser(Long id);
}
