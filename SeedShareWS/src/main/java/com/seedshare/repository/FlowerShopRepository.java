package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;

/**
 * Repository Class for FlowerShop
 * @author joao.silva
 */
public interface FlowerShopRepository extends PagingAndSortingRepository<FlowerShop, Long>{
	FlowerShop findByUser(User user);
	
	FlowerShop findByCnpj(String cnpj);
}
