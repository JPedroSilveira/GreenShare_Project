package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Fruit;

/**
 * Repository Interface of {@link com.seedshare.entity.Fruit}
 * 
 * @author joao.silva
 */
public interface FruitRepository extends PagingAndSortingRepository<Fruit, Long> {

}
