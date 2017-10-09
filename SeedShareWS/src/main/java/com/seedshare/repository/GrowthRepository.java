package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Growth;

/**
 * Repository Interface for Growth
 * 
 * @author joao.silva
 */
public interface GrowthRepository extends CrudRepository<Growth, Long> {

}