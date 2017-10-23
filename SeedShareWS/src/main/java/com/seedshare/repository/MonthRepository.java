package com.seedshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.Month;

/**
 * Repository Interface of {@link com.seedshare.entity.Month}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface MonthRepository extends CrudRepository<Month, Long>{

	List<Month> findAllByNumberIn(List<Short> number);
	
}
