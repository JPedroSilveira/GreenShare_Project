package com.greenshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Flower;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Flower}
 * 
 * @author joao.silva
 */
@Repository
public interface FlowerRepository extends PagingAndSortingRepository<Flower, Long> {

	Flower findOneBySpecies(Long id);

}
