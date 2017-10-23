package com.seedshare.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.address.Address;
import com.seedshare.entity.user.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.AddressRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of {@link com.seedshare.service.user.UserService} interface
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@Service
public class UserServiceImpl extends IsHelper implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public ResponseEntity<?> create(User user) {
		if (isNotNull(user)) {
			if (isUniqueEmail(user.getEmail())) {
				return new ResponseEntity<String>("Email já cadastrado.", HttpStatus.BAD_REQUEST);
			}
			if (isUniqueCPF(user.getCpf())) {
				return new ResponseEntity<String>("CPF já cadastrado.", HttpStatus.BAD_REQUEST);
			}
			Address address = user.getAddress();
			if (isNotNull(address)) {
				if (isNotNull(address.getId())) {
					address = addressRepository.findOne(address.getId());
					if (isNull(address) || address.isInUse()) {
						address = saveNewAddress(address);
					}
				} else {
					address = saveNewAddress(address);
				}
			} else {
				return new ResponseEntity<String>("Endereço não pode ser nulo.", HttpStatus.BAD_REQUEST);
			}
			User newUser = new User(user.getCpf(), user.getName(), user.getEmail(), user.getPassword(),
					user.getIsLegalPerson(), address);
			if (newUser.isValid()) {
				User response = userRepository.save(newUser);
				return new ResponseEntity<User>(response, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newUser.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	private Address saveNewAddress(Address address) {
		address = new Address(address);
		return addressRepository.save(address);
	}

	@Override
	public ResponseEntity<?> changePassword(User user) {
		if (isNotNull(user)) {
			User currentUser = getCurrentUser();
			if (user.hasValidPassword()) {
				currentUser.setPassword(user.getPassword());
				currentUser.encodePassword();
				currentUser = userRepository.save(currentUser);
				return new ResponseEntity<User>(currentUser, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Senha inválida.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> changeName(String name) {
		if (isNotNull(name)) {
			User currentUser = getCurrentUser();
			currentUser.setName(name);
			if (currentUser.hasValidName()) {
				currentUser = userRepository.save(currentUser);
				return new ResponseEntity<User>(currentUser, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nome inválido.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Nome não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			User userDB = userRepository.findOne(id);
			if (isNotNull(userDB)) {
				return new ResponseEntity<User>(userDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<?> findOneByEmail(String email) {
		if (isNotNull(email)) {
			User userDB = userRepository.findOneByEmail(email);
			if (isNotNull(userDB)) {
				return new ResponseEntity<User>(userDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Email não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	public User findOneUserByEmail(String email) {
		if (isNotNull(email)) {
			return userRepository.findOneByEmail(email);
		}
		return null;
	}

	private User findOneByCpf(String cpf) {
		if (isNotNull(cpf)) {
			return userRepository.findOneByCpf(cpf);
		}
		return null;
	}

	private Boolean isUniqueEmail(String email) {
		return isNull(this.findOneByEmail(email));
	}

	private Boolean isUniqueCPF(String cpf) {
		return isNull(this.findOneByCpf(cpf));
	}

}
