package com.seedshare.controller.offer;

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

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Offer;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;
import com.seedshare.service.offer.OfferServiceImpl;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/offer")
public class OfferControllerImpl implements OfferController {

	@Autowired
	OfferServiceImpl offerService;

	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(Offer offer) {
		return offerService.save(offer);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return offerService.delete(id);
	}

	@Override
	@DeleteMapping("/")
	public ResponseEntity<?> delete(@RequestBody Offer offer) {
		return offerService.delete(offer.getId());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return offerService.findOne(id);
	}

	@Override
	@GetMapping("/getByUser/")
	public ResponseEntity<?> findAllByUser(@RequestBody User user) {
		return offerService.findAllByUser(user.getId());
	}

	@Override
	@GetMapping("/getByUser/{id}")
	public ResponseEntity<?> findAllByUser(@PathVariable Long id) {
		return offerService.findAllByUser(id);
	}

	@Override
	@GetMapping("/getByFlowerShop/")
	public ResponseEntity<?> findAllByFlowerShop(@RequestBody FlowerShop flowerShop) {
		return offerService.findAllByFlowerShop(flowerShop.getId());
	}

	@Override
	@GetMapping("/getByFlowerShop/{id}")
	public ResponseEntity<?> findAllByFlowerShop(@PathVariable Long id) {
		return offerService.findAllByFlowerShop(id);
	}

	@Override
	@GetMapping("/getBySpecies/")
	public ResponseEntity<?> findAllBySpecies(@RequestBody Species species) {
		return offerService.findAllBySpecies(species.getId());
	}

	@Override
	@GetMapping("/getBySpecies/{id}")
	public ResponseEntity<?> findAllBySpecies(@PathVariable Long id) {
		return offerService.findAllBySpecies(id);
	}
}
