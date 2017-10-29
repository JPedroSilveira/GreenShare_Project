package com.greenshare.controller.offer;

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

import com.greenshare.entity.offer.Offer;
import com.greenshare.service.offer.OfferServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@RestController
@RequestMapping("/offer/")
public class OfferControllerImpl implements OfferController {

	@Autowired
	OfferServiceImpl offerService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Offer offer) {
		return offerService.save(offer);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return offerService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return offerService.findOne(id);
	}

	@Override
	@GetMapping("user/{id}")
	public ResponseEntity<?> findAllByUser(@PathVariable Long id) {
		return offerService.findAllByUser(id);
	}

	@Override
	@GetMapping("flower_shop/{id}")
	public ResponseEntity<?> findAllByFlowerShop(@PathVariable Long id) {
		return offerService.findAllByFlowerShop(id);
	}
	
	@Override
	@GetMapping("species/{id}")
	public ResponseEntity<?> findAllBySpecies(@PathVariable Long id) {
		return offerService.findAllBySpecies(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Offer offer) {
		return offerService.update(offer);
	}

	@Override
	@GetMapping("state/{id}")
	public ResponseEntity<?> findAllByState(@PathVariable Long id) {
		return offerService.findAllByState(id);
	}

	@Override
	@GetMapping("city/{id}")
	public ResponseEntity<?> findAllByCity(@PathVariable Long id) {
		return offerService.findAllByCity(id);
	}

	@Override
	@GetMapping("current_user/")
	public ResponseEntity<?> findAllByCurrentUser() {
		return offerService.findAllByCurrentUser();
	}
}
