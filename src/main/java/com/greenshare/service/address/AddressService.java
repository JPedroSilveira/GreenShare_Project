package com.greenshare.service.address;

import org.springframework.http.ResponseEntity;

import com.greenshare.entity.address.Address;

/**
 * Service interface of {@link com.greenshare.entity.address.Address}
 * 
 * @author joao.silva
 */
public interface AddressService {

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> update(Address address);
    
    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> save(Address address);
   
}