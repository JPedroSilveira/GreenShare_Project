package com.seedshare.service.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.AddressRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of Address Service interface
 * @author joao.silva
 */
@Service
public class AddressServiceImpl extends IsHelper implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Address save(Address address) {
		Address newAddress = new Address(address.getLatitude(), address.getLongitude(), getCurrentUser());
		if(newAddress.isValid()) {
			newAddress = addressRepository.save(address);
			newAddress.getUser().cleanPrivateDate();
			return newAddress;
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		if(isNotNull(id)) {
			addressRepository.delete(id);
		}
	}

	@Override
	public Address findOne(Long id) {
		if(isNotNull(id)) {
			Address addressDB = addressRepository.findOne(id);
			if(isNotNull(addressDB)) {
				addressDB.getUser().cleanPrivateDate();
				return addressDB;
			}
		}
		return null;
	}
	
	@Override
	public List<Address> findAllByCurrentUser() {
		User userDB = getCurrentUser();
		if(isNotNull(userDB)) {
			List<Address> addressListDB = addressRepository.findAllByUser(userDB);
			addressListDB.forEach(address -> address.getUser().cleanPrivateDate());
			return addressListDB;
		}
		return null;
	}
}
