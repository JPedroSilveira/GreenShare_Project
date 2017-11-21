package com.greenshare.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.vegetable.Climate;
import com.greenshare.entity.vegetable.Growth;
import com.greenshare.entity.vegetable.Soil;

/**
 * Repository Interface of {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

	Iterable<Offer> findAllByUserAndOfferStatus(Long id, Integer offerStatus);
	
	List<Offer> findAllBySpeciesAndOfferStatus(Long id, Integer offerStatus);

	Iterable<Offer> findAllByUserAddressCityStateAndOfferStatus(Long id, Integer offerStatus);

	Iterable<Offer> findAllByUserAddressCityAndOfferStatus(Long id, Integer offerStatus);
	
	Iterable<Offer> findAllByUser(Long id);
	
	/*Species without Flower and Fruit*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndOfferStatusAndSpeciesFruitIsNullAndSpeciesFlowerIsNull(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Integer offerStatus, Pageable pageable);
	
	/*Species without Flower*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndOfferStatusAndSpeciesFlowerIsNull(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Integer offerStatus, Pageable pageable);
	
	/*Species without Fruit*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndOfferStatusAndSpeciesFruitIsNull(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Integer offerStatus, Pageable pageable);
	
	/*Species with Fruit and Flower*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndOfferStatus(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Integer offerStatus, Pageable pageable);

}
