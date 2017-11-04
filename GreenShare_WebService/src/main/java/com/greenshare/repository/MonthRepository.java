package com.greenshare.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.Month;

/**
 * Repository Interface of {@link com.greenshare.entity.Month}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface MonthRepository extends CrudRepository<Month, Long>{

	List<Month> findAllByNumberIn(List<Short> number);
	
}
