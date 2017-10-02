package com.seedshare.service.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;
import com.seedshare.repository.AddressRepository;

/**
 * Implementation of Address Service interface
 * @author joao.silva
 */
@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void delete(Address address) {
		addressRepository.delete(address);		
	}

	@Override
	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}
	
	@Override
	public List<Address> findAllByUser(User user) {
		return addressRepository.findAllByUser(user);
	}
}
