package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenshare.entity.vegetable.Flower;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Flower}
 * 
 * @author joao.silva
 */
public interface FlowerRepository extends PagingAndSortingRepository<Flower, Long> {

	Flower findOneBySpecies(Long id);

}
