package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;

/**
 * Repository Interface for Offer
 * 
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

	Iterable<Offer> findAllByUser(User user);

	Iterable<Offer> findAllBySpecies(Species species);
}
