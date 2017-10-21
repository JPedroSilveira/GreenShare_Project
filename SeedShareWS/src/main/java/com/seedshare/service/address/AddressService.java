package com.seedshare.service.address;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Address;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.Address}
 * @author joao.silva
 */
public interface AddressService extends BasicService<Address,Long>{

	ResponseEntity<?> findAllByCurrentUser();
	
}