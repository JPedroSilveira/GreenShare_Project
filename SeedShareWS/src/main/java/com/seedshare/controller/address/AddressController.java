package com.seedshare.controller.address;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.address.Address;

/**
 * @author joao.silva
 */
public interface AddressController {

	ResponseEntity<?> save(Address address);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findOneById(Long id);

	ResponseEntity<?> findAllByCurrentUser();

}
