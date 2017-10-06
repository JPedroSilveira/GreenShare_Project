package com.seedshare.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Species;

/**
 * Repository Class for Species
 * @author joao.silva
 */
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Long>{
	List<Species> findOneByCommonName(String commonName);
	List<Species> findOneByScientificName(String scientificName);
}
