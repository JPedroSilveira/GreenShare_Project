package com.seedshare.service.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.address.Address;
import com.seedshare.entity.address.City;
import com.seedshare.entity.user.User;
import com.seedshare.enumeration.AddressType;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.AddressRepository;
import com.seedshare.repository.CityRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation Service of
 * {@link com.seedshare.service.address.AddressService}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@Service
public class AddressServiceImpl extends IsHelper implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CityRepository cityRepository;

	@Override
	public ResponseEntity<?> save(Address address) {
		if (isNotNull(address)) {
			City city = address.getCity();
			if(isNotNull(city) && isNotNull(city.getId())) {
				city = cityRepository.findOne(city.getId());
				if(isNotNull(city)) {
					Address newAddress = new Address(city, address.getNumber(), address.getNeighborhood(), address.getReference(),address.getAddressName(),address.getComplement(),address.getType());
					if(AddressType.exists(address.getType())) {
						if (newAddress.isValid()) {
							newAddress = addressRepository.save(newAddress);
							return new ResponseEntity<Address>(newAddress, HttpStatus.OK);
						}
					}
					return new ResponseEntity<String>("Tipo de endereço inválido.", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Cidade não pode ser nula ou ter id nulo.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Endereço inválido.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			Address address = addressRepository.findOne(id);
			if (address.getUser().getId() == getCurrentUser().getId()) {
				addressRepository.delete(id);
				return new ResponseEntity<String>("Endereço deletado.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Endereço não pertence ao usuário atual.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("É necessário informar um ID.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Address addressDB = addressRepository.findOne(id);
			if (isNotNull(addressDB)) {
				return new ResponseEntity<Address>(addressDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Endereço não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("É necessário informar um ID.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCurrentUser() {
		User userDB = getCurrentUser();
		if (isNotNull(userDB)) {
			Iterable<Address> addressListDB = addressRepository.findAllByUser(userDB);
			return new ResponseEntity<Iterable<Address>>(addressListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Usuário logado inválido.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Address address) {
		if(isNotNull(address)) {
			Address addressDB = addressRepository.findOne(address.getId());
			if(isNotNull(addressDB)) {
				if(addressDB.getUser().getId() == getCurrentUserId()) {
					addressDB.update(address);
					if(addressDB.isValid()) {
						addressDB = addressRepository.save(addressDB);
						return new ResponseEntity<Address>(addressDB, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(addressDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Endereço não pertence ao usuário atual.", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Endereço não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Endereço atualizado não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
