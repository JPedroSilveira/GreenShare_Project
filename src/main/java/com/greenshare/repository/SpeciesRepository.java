package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Species;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Species}
 * 
 * @author joao.silva
 */
@Repository
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Long> {
	
	Iterable<Species> findOneByCommonNameAndEnabledTrue(String commonName);

	Iterable<Species> findOneByScientificNameAndEnabledTrue(String scientificName);
	
}
