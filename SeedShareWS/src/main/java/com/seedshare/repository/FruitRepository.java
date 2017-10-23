package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.vegetable.Fruit;

/**
 * Repository Interface of {@link com.seedshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 */
public interface FruitRepository extends PagingAndSortingRepository<Fruit, Long> {

	Fruit findOneBySpecies(Long id);

}
