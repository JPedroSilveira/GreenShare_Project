package com.seedshare.controller.address;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.Address;

/**
 * @author joao.silva
 */
public interface AddressController{

	ResponseEntity<?> addAddress(Address address);
	
	ResponseEntity<?> deleteAddress(Long id);
	
	ResponseEntity<?> getAddressById(Long id);

	ResponseEntity<?> getAllByUser();
}
