package com.greenshare.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.vegetable.Climate;
import com.greenshare.entity.vegetable.Growth;
import com.greenshare.entity.vegetable.Soil;
import com.greenshare.entity.vegetable.Species;

/**
 * Repository Interface of {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 */
@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

	List<Offer> findAllByUser(Long id, Pageable pageable);
	
	List<Offer> findAllBySpecies(Species species, Pageable pageable);

	List<Offer> findAllByUserAddressCityState(Long id, Pageable pageable);

	List<Offer> findAllByUserAddressCity(Long id, Pageable pageable);
		
	/*Species without Flower and Fruit*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndSpeciesFruitIsNullAndSpeciesFlowerIsNull(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Pageable pageable);
	
	/*Species without Flower*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndSpeciesFlowerIsNull(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Pageable pageable);
	
	/*Species without Fruit*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndSpeciesFruitIsNull(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Pageable pageable);
	
	/*Species with Fruit and Flower*/
	List<Offer> findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamental(List<Growth> growthList, List<Soil> soilList, List<Climate> climateList, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Integer rootDepth, Integer averageHeight, Pageable pageable);

}
