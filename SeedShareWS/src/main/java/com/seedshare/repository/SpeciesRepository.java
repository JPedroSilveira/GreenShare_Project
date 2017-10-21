package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Species;

/**
 * Repository Interface of {@link com.seedshare.entity.Species}
 * 
 * @author joao.silva
 */
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Long> {
	Iterable<Species> findOneByCommonName(String commonName);

	Iterable<Species> findOneByScientificName(String scientificName);
}
