package com.greenshare.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.greenshare.service.address.AddressServiceImpl;

/**
 * Controller Class for {@link com.greenshare.entity.address.Address}
 * 
 * @author joao.silva
 */
@RestController
@RequestMapping("/address/")
public class AddressControllerImpl implements AddressController {

	@Autowired
	AddressServiceImpl addressService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Address address) {
		return addressService.save(address);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return addressService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOneById(@PathVariable Long id) {
		return addressService.findOne(id);
	}

	@Override
	@GetMapping("currentUser")
	public ResponseEntity<?> findAllByCurrentUser() {
		return addressService.findAllByCurrentUser();
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Address address) {
		return addressService.update(address);

	}
}
