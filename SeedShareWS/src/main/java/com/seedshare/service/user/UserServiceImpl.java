package com.seedshare.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
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

	@Override
	public ResponseEntity<?> create(User user) {
		if (isNotNull(user)) {
			User newUser = new User(user.getCpf(), user.getName(), user.getEmail(), user.getPassword(),
					user.getIsLegalPerson());
			if (newUser.isValid() && validUniqueKeys(newUser)) {
				User response = userRepository.save(newUser);
				return new ResponseEntity<User>(response, HttpStatus.OK);
			} else {
				if (!isUniqueEmail(newUser.getEmail())) {
					newUser.addValidationError("Email já cadastrado");
				}
				if (!isUniqueCPF(newUser.getCpf())) {
					newUser.addValidationError("CPF já cadastrado");
				}
			}
			return new ResponseEntity<List<String>>(newUser.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
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
			User userDB = userRepository.findOneByEmail(email);
			if (isNotNull(userDB)) {
				return userDB;
			}
			return null;
		}
		return null;
	}

	private User findOneByCpf(String cpf) {
		return userRepository.findOneByCpf(cpf);
	}

	private Boolean validUniqueKeys(User user) {
		return this.isUniqueEmail(user.getEmail()) && this.isUniqueCPF(user.getCpf());
	}

	private Boolean isUniqueEmail(String email) {
		if (isNotNull(email)) {
			return this.findOneByEmail(email) == null;
		}
		return true;
	}

	private Boolean isUniqueCPF(String cpf) {
		if (isNotNull(cpf)) {
			return this.findOneByCpf(cpf) == null;
		}
		return true;
	}

}
