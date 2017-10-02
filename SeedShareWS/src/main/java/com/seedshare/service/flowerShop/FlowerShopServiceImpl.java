package com.seedshare.service.flowerShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;
import com.seedshare.repository.FlowerShopRepository;

/**
 * Implementation of FlowerShop Service interface
 * @author joao.silva
 */
@Service
public class FlowerShopServiceImpl implements FlowerShopService{

	@Autowired
	FlowerShopRepository flowerShopRepository;
	
	@Override
	public FlowerShop save(FlowerShop flowerShop) {
		return flowerShopRepository.save(flowerShop);
	}

	@Override
	public void delete(FlowerShop flowerShop) {
		flowerShopRepository.delete(flowerShop);
	}

	@Override
	public FlowerShop findOne(Long id) {
		return flowerShopRepository.findOne(id);
	}

	@Override
	public FlowerShop findByUser(User user) {
		return flowerShopRepository.findByUser(user);
	}
}
