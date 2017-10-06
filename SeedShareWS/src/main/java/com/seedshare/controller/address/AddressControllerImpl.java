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

import com.seedshare.entity.Address;
import com.seedshare.helpers.IsHelper;
import com.seedshare.service.address.AddressServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/address")
public class AddressControllerImpl extends IsHelper implements AddressController {

	@Autowired
	AddressServiceImpl addressService;
	
	@Override
	@PostMapping("/")
	public ResponseEntity<?> addAddress(@RequestBody Address address) {
		Address response = addressService.save(address);
		if(isNotNull(response)) {
			return new ResponseEntity<Address>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Falha ao registrar endereço", HttpStatus.BAD_REQUEST);   
    }
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
		addressService.delete(id);
		return new ResponseEntity<String>("Endereço deletado", HttpStatus.OK);   
    }
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable Long id) {
		Address address = addressService.findOne(id);
		if(address != null && address.getUser().getId() == getCurrentUser().getId()) {
			return new ResponseEntity<Address>(address, HttpStatus.OK);   
		}else {
			return new ResponseEntity<String>("Endereço não encontrado", HttpStatus.NOT_FOUND);
		}
    }
	
	@Override
	@GetMapping("/all")
	public ResponseEntity<?> getAllByUser() {
		List<Address> addresses = addressService.findAllByCurrentUser();
		if(addresses != null) {
			return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);   
		} else {
			return new ResponseEntity<String>("Nenhum endereço encontrado", HttpStatus.NOT_FOUND);
		}
    }
}
