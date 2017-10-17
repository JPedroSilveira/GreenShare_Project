package com.seedshare.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;

/**
 * Repository Interface for FlowerShop
 * 
 * @author joao.silva
 */
public interface FlowerShopRepository extends PagingAndSortingRepository<FlowerShop, Long> {
	FlowerShop findOneByUser(User user);

	FlowerShop findOneByCnpj(String cnpj);

	@Query(value = "select fs from FlowerShop fs where fs.address.city.state.id = ?1")
	Iterable<FlowerShop> findAllByState(Long id);
	
	@Query(value = "select fs from FlowerShop fs where fs.address.city.id = ?1")
	Iterable<FlowerShop> findAllByCity(Long id);
}
