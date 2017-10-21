package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Growth;

/**
 * Repository Interface of {@link com.seedshare.entity.Growth}
 * 
 * @author joao.silva
 */
public interface GrowthRepository extends CrudRepository<Growth, Long> {

}