package com.seedshare.service.flowerShop;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;
import com.seedshare.service.BasicService;

/**
 * Service interface of FlowerShop
 * @author joao.silva
 */
public interface FlowerShopService extends BasicService<FlowerShop,Long>{
	FlowerShop findByUser(User user);
}
