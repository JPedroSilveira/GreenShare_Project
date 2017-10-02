package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.Fruit;

/**
 * Repository Class for Fruit
 * @author joao.silva
 */
public interface FruitRepository extends PagingAndSortingRepository<Fruit, Long>{

}
