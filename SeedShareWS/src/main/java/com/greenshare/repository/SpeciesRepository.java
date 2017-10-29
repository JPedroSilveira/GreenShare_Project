package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenshare.entity.vegetable.Species;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Species}
 * 
 * @author joao.silva
 */
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Long> {
	
	Iterable<Species> findOneByCommonNameAndEnabledTrue(String commonName);

	Iterable<Species> findOneByScientificNameAndEnabledTrue(String scientificName);
	
}
