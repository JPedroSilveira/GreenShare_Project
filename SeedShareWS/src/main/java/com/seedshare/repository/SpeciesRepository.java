package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.vegetable.Species;

/**
 * Repository Interface of {@link com.seedshare.entity.vegetable.Species}
 * 
 * @author joao.silva
 */
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Long> {
	
	Iterable<Species> findOneByCommonNameAndActiveTrue(String commonName);

	Iterable<Species> findOneByScientificNameAndActiveTrue(String scientificName);
	
}
