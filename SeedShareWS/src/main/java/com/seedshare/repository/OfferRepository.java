package com.seedshare.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Offer;

/**
 * Repository Interface for Offer
 * 
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	Iterable<Offer> findAllByUser(Long id);

	Iterable<Offer> findAllBySpecies(Long id);

	Iterable<Offer> findAllByFlowerShop(Long id);

	@Query(value = "select offer from Offer offer where offer.address.city.state.id = ?1")
	Iterable<Offer> findAllByState(Long id);

	@Query(value = "select offer from Offer offer where offer.address.city.id = ?1")
	Iterable<Offer> findAllByCity(Long id);

}
