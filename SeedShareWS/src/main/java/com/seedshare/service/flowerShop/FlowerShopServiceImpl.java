package com.seedshare.service.flowerShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of FlowerShop Service interface
 * @author joao.silva
 */
@Service
public class FlowerShopServiceImpl extends IsHelper implements FlowerShopService{

	@Autowired
	FlowerShopRepository flowerShopRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public FlowerShop save(FlowerShop flowerShop) {
		User currentUser = getCurrentUser();
		if(currentUser.getIsLegalPerson()) {
			FlowerShop flowerShopDB = flowerShopRepository.findByUser(currentUser);
			if(flowerShopDB == null) {
				flowerShopDB = flowerShopRepository.findByCnpj(flowerShop.getCnpj());
				if(flowerShopDB == null || flowerShopDB.getIsActive() == false) {
					FlowerShop newFlowerShop = new FlowerShop(flowerShop.getCnpj(),flowerShop.getDescription(),currentUser);
					if(newFlowerShop.isValid()) {
						FlowerShop response = flowerShopRepository.save(newFlowerShop);
						response.getUser().cleanPrivateDate();
						return response;
					}
				}			
			}else if(flowerShopDB.getIsActive()){
				flowerShopDB.setDescription(flowerShop.getDescription());
				flowerShopDB.setName(flowerShop.getName());
				if(flowerShopDB.isValid()) {
					FlowerShop response = flowerShopRepository.save(flowerShopDB);
					response.getUser().cleanPrivateDate();
					return response;
				}
			}
		}
		return null;   
	}

	@Override
	public void delete(Long id) {
		if(isNotNull(id)) {
			FlowerShop flowerShop = flowerShopRepository.findOne(id);
			if(flowerShop.getUser().getId() == getCurrentUserId()) {
				flowerShop.setIsActive(false);
				flowerShopRepository.save(flowerShop);
			}
		}
	}

	@Override
	public FlowerShop findOne(Long id) {
		if(isNotNull(id)) {
			FlowerShop response = flowerShopRepository.findOne(id);
			if(isNotNull(response)) {
				response.getUser().cleanPrivateDate();
				return response;
			}
		}
		return null;
	}
	
	@Override
	public FlowerShop findByCnpj(String cnpj) {
		if(isNotNull(cnpj)) {
			FlowerShop response = flowerShopRepository.findByCnpj(cnpj);
			if(isNotNull(response)) {
				response.getUser().cleanPrivateDate();
				return response;
			}
		}
		return null;
	}

	@Override
	public FlowerShop findByCurrentUser() {
		User currentUser = getCurrentUser();
		if(isNotNull(currentUser) && currentUser.getIsLegalPerson()) {
			FlowerShop response = flowerShopRepository.findByUser(getCurrentUser());
			if(isNotNull(response)) {
				response.getUser().cleanPrivateDate();
				return response;
			}
		}
		return null;
	}
	
	@Override
	public FlowerShop findByUser(Long id) {
		if(isNotNull(id)) {
			User userDB = userRepository.findOne(id);
			if(isNotNull(userDB) && userDB.getIsLegalPerson()) {
				FlowerShop response = flowerShopRepository.findByUser(userDB);
				if(isNotNull(response)) {
					response.getUser().cleanPrivateDate();
					return response;
				}
			}
		}
		return null;
	}
}
