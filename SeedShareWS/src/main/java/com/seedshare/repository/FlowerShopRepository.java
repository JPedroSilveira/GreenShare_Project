package com.seedshare.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.user.User;

/**
 * Repository Interface of {@link com.seedshare.entity.FlowerShop}
 * 
 * @author joao.silva
 */
public interface FlowerShopRepository extends PagingAndSortingRepository<FlowerShop, Long> {

	FlowerShop findOneByUser(User user);

	FlowerShop findOneByCnpj(String cnpj);

	Iterable<FlowerShop> findAllByAddressCityStateAndIsActiveTrue(Long id);

	Iterable<FlowerShop> findAllByAddressCityAndIsActiveTrue(Long id);

}
