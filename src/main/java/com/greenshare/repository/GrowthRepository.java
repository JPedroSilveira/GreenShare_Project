package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.vegetable.Growth;

/**
 * Repository Interface of {@link com.greenshare.entity.vegetable.Growth}
 * 
 * @author joao.silva
 */
@Repository
public interface GrowthRepository extends CrudRepository<Growth, Long> {

}