package com.greenshare.controller.address;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.address.Address;

/**
 * Controller interface of {@link com.greenshare.entity.address.Address}
 * 
 * @author joao.silva
 */
public interface AddressController {

	ResponseEntity<?> save(Address address);

	ResponseEntity<?> delete(Long id);

	ResponseEntity<?> findOneById(Long id);

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> update(Address address);

}
