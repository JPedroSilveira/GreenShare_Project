package com.seedshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;

/**
 * Repository Class for Offer
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>{
	
	List<Offer> findAllByUser(User user);
	
	List<Offer> findAllBySpecies(Species species);
}
