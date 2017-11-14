package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Fruit;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Fruit}
 * 
 * @author joao.silva
 */
@Repository
public interface FruitRepository extends PagingAndSortingRepository<Fruit, Long> {

	Fruit findOneBySpecies(Long id);

}
