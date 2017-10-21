package com.seedshare.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Address;
import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.AddressRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation Service of
 * {@link com.seedshare.service.address.AddressService}
 * 
 * @author joao.silva
 */
@Service
public class AddressServiceImpl extends IsHelper implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<?> save(Address address) {
		if (isNotNull(address)) {
			Address newAddress = new Address(address.getCity(), getCurrentUser(), address.getNumero(),
					address.getBairro(), address.getEndereco(), address.getComplemento(), address.getReferencia());
			if (newAddress.isValid()) {
				newAddress = addressRepository.save(newAddress);
				return new ResponseEntity<Address>(newAddress, HttpStatus.OK);
			}
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
				addressDB.setUser(null);
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
}
