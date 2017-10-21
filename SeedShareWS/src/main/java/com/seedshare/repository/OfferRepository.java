package com.seedshare.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Offer;

/**
 * Repository Interface of {@link com.seedshare.entity.Offer}
 * 
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	@Query(value = "select of from Offer of where of.user = ?1 and offerStatus = 0")
	Iterable<Offer> findAllByUser(Long id);
	
	@Query(value = "select of from Offer of where of.flowerShop = ?1 and offerStatus = 0")
	Iterable<Offer> findAllBySpecies(Long id);

	@Query(value = "select of from Offer of where of.flowerShop = ?1 and offerStatus = 0")
	Iterable<Offer> findAllByFlowerShop(Long id);

	@Query(value = "select offer from Offer offer where offer.address.city.state.id = ?1 and offerStatus = 0")
	Iterable<Offer> findAllByState(Long id);

	@Query(value = "select offer from Offer offer where offer.address.city.id = ?1 and offerStatus = 0")
	Iterable<Offer> findAllByCity(Long id);
	
	@Query(value = "select of from Offer of where of.user = ?1")
	Iterable<Offer> findAllByCurrentUser(Long id);
}
