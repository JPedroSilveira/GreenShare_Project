package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Soil;

/**
 * Repository Class for Soil
 * @author joao.silva
 */
@Repository
public interface SoilRepository extends CrudRepository<Soil, Long>{

}