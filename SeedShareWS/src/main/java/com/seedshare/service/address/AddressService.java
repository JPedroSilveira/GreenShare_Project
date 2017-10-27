package com.seedshare.service.address;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.address.Address;

/**
 * Service interface of {@link com.seedshare.entity.address.Address}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface AddressService {

	ResponseEntity<?> findAllByCurrentUser();

	ResponseEntity<?> update(Address address);
    
    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> findOne(Long id);

	ResponseEntity<?> save(Address address);
   
}