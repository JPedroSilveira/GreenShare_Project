package com.greenshare.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenshare.entity.address.Address;
import com.greenshare.entity.address.City;
import com.greenshare.entity.address.Country;
import com.greenshare.entity.address.State;
import com.greenshare.entity.user.User;
import com.greenshare.helpers.IsHelper;
import com.greenshare.service.user.UserServiceImpl;

/**
 * Controller implementation of
 * {@link com.greenshare.controller.user.UserController}
 *
 * @author gabriel.schneider
 * @author joao.silva
 */
@RestController
@RequestMapping("/user/")
public class UserControllerImpl extends IsHelper implements UserController {
	@Autowired
	UserServiceImpl userService;

	@Override
	@PutMapping(value = "password/")
	public ResponseEntity<?> changePassword(@RequestBody User user) {
		return userService.changePassword(user);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> updateWithOutPassword(@RequestBody User user) {
		return userService.updateUserLoggedIn(user);
	}

	@Override
	@PostMapping(value = "register/")
	public ResponseEntity<?> create(@RequestBody User user) {
		return userService.create(user);
	}

	@Override
	@PutMapping("valid_email/")
	public ResponseEntity<?> validEmail(@RequestBody Email email) {
		return userService.validEmail(email.email);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return userService.findOne(id);
	}

	@GetMapping("entity_example/")
	public ResponseEntity<?> entityExample() {
		Country country = new Country("String::length: >=1 and <=100");
		State state = new State("String::length: >=1 and <=100", country);
		City city = new City("String::length: >=1 and <=100", state);
		Address address = new Address(city, 2, "String::length: >=1 and <=200", "String::length: >=1 and <=200",
				"String::Nullable::length: <=200", "String::Nullable::length: <=200", 1);
		User user = new User("String::length: == 11",
				"String::length: >= 11 and <= 100", "String::length: >=1 and <=100", "String::length: >=8 and <=250",
				false, address, "51984401159", null);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteByID(@PathVariable Long id) {
		return userService.deleteByID(id);
	}

	@Override
	@DeleteMapping("")
	public ResponseEntity<?> deleteByCurrentUser() {
		return userService.deleteByCurrentUser();
	}
}
