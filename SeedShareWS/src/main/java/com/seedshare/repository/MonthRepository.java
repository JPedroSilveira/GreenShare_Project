package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Month;

/**
 * Repository Class for Month
 * @author joao.silva
 */
@Repository
public interface MonthRepository extends CrudRepository<Month, Long>{

}
