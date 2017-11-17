package com.greenshare.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.address.Address;
import com.greenshare.entity.user.User;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.UserRepository;
import com.greenshare.service.address.AddressServiceImpl;

/**
 * Implementation of {@link com.greenshare.service.user.UserService} interface
 * 
 * @author joao.silva
 */
@Service
public class UserServiceImpl extends IsHelper implements UserService {

	@Autowired
	UserRepository userRepository;

	AddressServiceImpl addressService;

	@Override
	public ResponseEntity<?> create(User user) {
		if (isNotNull(user)) {
			if (!isUniqueEmail(user.getEmail())) {
				return new ResponseEntity<String>("Email já cadastrado.", HttpStatus.CONFLICT);
			}
			if (isNotNull(user.getCpf()) && !isUniqueCPF(user.getCpf())) {
				return new ResponseEntity<String>("CPF já cadastrado.", HttpStatus.CONFLICT);
			}
			Address address = null;
			if(isNotNull(user.getAddress())) {
				address = new Address(user.getAddress());
			}
			if(isNotNull(user.getFlowerShop())) {
				FlowerShop fs = user.getFlowerShop();
				FlowerShop flowerShop = new FlowerShop(fs.getCnpj(), fs.getDescription(), fs.getName(), address);
				user.setFlowerShop(flowerShop);
			}
			User newUser = new User(user.getCpf(), user.getName(), user.getEmail(),
					user.getPassword(), user.getIsLegalPerson(), address, user.getPhoneNumber(), user.getFlowerShop());
			newUser.setInsertionDate();
			if (newUser.isValid()) {
				newUser = userRepository.save(newUser);
				return new ResponseEntity<User>(newUser, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newUser.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<String> validEmail(String email){
		if(isNotNull(email)) {
			if(isNull(findOneUserByEmail(email))){
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
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
				userDB.clearPrivateData();
				return new ResponseEntity<User>(userDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
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
	
	@Override
	public ResponseEntity<?> addCPF(String cpf) {
		if (isNotNull(cpf)) {
			User userDB = getCurrentUser();
			if (isNotNull(userDB)) {
				if(isNullOrEmpty(userDB.getCpf())) {
					userDB.setCpf(cpf);
					if(userDB.isValid()) {
						return new ResponseEntity<User>(userDB, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(userDB.getValidationErrors(), HttpStatus.BAD_REQUEST);					
				}
				return new ResponseEntity<String>("Usuário já possui CPF cadastrado.", HttpStatus.CONFLICT);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Email não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> deleteByID(Long id) {
		if (isNotNull(id)) {
			User userDB = userRepository.findOne(id);
			if (isNotNull(userDB)) {
				userRepository.delete(userDB.getId());
				return new ResponseEntity<String>("Usuário deletado.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> deleteByCurrentUser() {
		User userDB = getCurrentUser();
		if (isNotNull(userDB)) {
			userRepository.delete(userDB.getId());
			return new ResponseEntity<String>("Usuário deletado.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> updateUserLoggedIn(User user) {
		if (isNotNull(user)) {
			User userDB = getCurrentUser();
			if (isNotNull(userDB)) {
				userDB.update(user);
				if (userDB.isValid()) {
					userDB = userRepository.save(userDB);
					return new ResponseEntity<User>(userDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(userDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Nenhum usuário logado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Usuário atualizado não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public User findOneUserByEmail(String email) {
		if (isNotNull(email)) {
			return userRepository.findOneByEmail(email);
		}
		return null;
	}

	private User _findOneByCpf(String cpf) {
		if (isNotNull(cpf)) {
			return userRepository.findOneByCpf(cpf);
		}
		return null;
	}

	private User _findOneByEmail(String email) {
		if (isNotNull(email)) {
			return userRepository.findOneByEmail(email);
		}
		return null;
	}

	private Boolean isUniqueEmail(String email) {
		return isNull(this._findOneByEmail(email));
	}

	private Boolean isUniqueCPF(String cpf) {
		return isNull(this._findOneByCpf(cpf));
	}

}
