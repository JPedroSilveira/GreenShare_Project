package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.vegetable.Flower;

/**
 * Repository Interface of {@link com.seedshare.entity.vegetable.Flower}
 * 
 * @author joao.silva
 */
public interface FlowerRepository extends PagingAndSortingRepository<Flower, Long> {

	Flower findOneBySpecies(Long id);

}
