package com.seedshare.service.address;

import java.util.List;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;
import com.seedshare.service.BasicService;

/**
 * Service interface of Address
 * @author joao.silva
 */
public interface AddressService extends BasicService<Address,Long>{

	List<Address> findAllByUser(User user);
	
}