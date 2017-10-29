package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenshare.entity.vegetable.Fruit;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 */
public interface FruitRepository extends PagingAndSortingRepository<Fruit, Long> {

	Fruit findOneBySpecies(Long id);

}
