package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Species;

/**
 * Repository Class for Species
 * @author joao.silva
 */
public interface SpeciesRepository extends PagingAndSortingRepository<Species, Long>{

}
