package com.seedshare.service.address;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.address.Address;
import com.seedshare.service.BasicService;

/**
 * Service interface of {@link com.seedshare.entity.address.Address}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface AddressService extends BasicService<Address, Long> {

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> update(Address address);

}