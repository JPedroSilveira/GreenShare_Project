package com.seedshare.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedshare.UserUtils;
import com.seedshare.entity.Address;
import com.seedshare.entity.User;
import com.seedshare.service.address.AddressServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/user/address")
public class AddressControllerImpl extends UserUtils implements AddressController {

	@Autowired
	AddressServiceImpl addressService;
	
	@Override
	@PostMapping("/add/")
	public ResponseEntity<?> addAddress(@RequestBody Address address) {
		User currentUser = getCurrentUser();
		Address newAddress = new Address(address.getLatitude(), address.getLongitude(), currentUser);
		Boolean validAddress = newAddress.isValid();
		if(validAddress) {
			if(addressService.save(newAddress) != null) {
				return new ResponseEntity<String>("Endereço registrado com sucesso", HttpStatus.OK);   
			}else {
				return new ResponseEntity<String>("Falha ao registrar endereço", HttpStatus.INTERNAL_SERVER_ERROR);   
			}
		}
		return new ResponseEntity<List<String>>(currentUser.getValidationErrors(), HttpStatus.BAD_REQUEST);   
    }
	
	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
		Address address = addressService.findOne(id);
		if(address != null && address.getUser().getId() == getCurrentUser().getId()) {
			addressService.delete(address);
			return new ResponseEntity<String>("Endereço deletado", HttpStatus.OK);   
		}else {
			return new ResponseEntity<String>("Endereço inválido", HttpStatus.BAD_REQUEST);
		}
    }
	
	@Override
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable Long id) {
		Address address = addressService.findOne(id);
		if(address != null && address.getUser().getId() == getCurrentUser().getId()) {
			return new ResponseEntity<Address>(address, HttpStatus.OK);   
		}else {
			return new ResponseEntity<String>("Endereço não encontrado", HttpStatus.NOT_FOUND);
		}
    }
	
	@Override
	@GetMapping("/get/all")
	public ResponseEntity<?> getAllByUser() {
		List<Address> addresses = addressService.findAllByUser(getCurrentUser());
		if(addresses != null) {
			return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);   
		} else {
			return new ResponseEntity<String>("Endereço não encontrado", HttpStatus.NOT_FOUND);
		}
    }
}
