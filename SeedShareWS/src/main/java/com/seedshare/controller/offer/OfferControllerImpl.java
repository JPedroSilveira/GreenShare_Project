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
public class OfferControllerImpl implements OfferController{

	@Autowired
	OfferServiceImpl offerService;
	
	@Override
	@PostMapping("/")
	public ResponseEntity<?> save(Offer offer) {
		Offer response = offerService.save(offer);
		if(response != null) {
			return new ResponseEntity<Offer>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Falha ao registrar oferta", HttpStatus.BAD_REQUEST);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		offerService.delete(id);
		return new ResponseEntity<String>("Oferta retirada.", HttpStatus.OK);
	}
	
	@Override
	@DeleteMapping("/")
	public ResponseEntity<?> delete(@RequestBody Offer offer) {
		offerService.delete(offer.getId());
		return new ResponseEntity<String>("Oferta retirada.", HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		Offer response = offerService.findOne(id);
		if(response != null) {
			return new ResponseEntity<Offer>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Falha ao registrar oferta", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@Override
	@GetMapping("/getByUser/")
	public ResponseEntity<?> findAllByUser(@RequestBody User user) {
		List<Offer> response = offerService.findAllByUser(user.getId());
		return findAllResponse(response);
	}

	@Override
	@GetMapping("/getByUser/{id}")
	public ResponseEntity<?> findAllByUser(@PathVariable Long id) {
		List<Offer> response = offerService.findAllByUser(id);
		return findAllResponse(response);
	}
	
	@Override
	@GetMapping("/getByFlowerShop/")
	public ResponseEntity<?> findAllByFlowerShop(@RequestBody FlowerShop flowerShop) {
		List<Offer> response = offerService.findAllByFlowerShop(flowerShop.getId());
		return findAllResponse(response);
	}
	
	@Override
	@GetMapping("/getByFlowerShop/{id}")
	public ResponseEntity<?> findAllByFlowerShop(@PathVariable Long id) {
		List<Offer> response = offerService.findAllByFlowerShop(id);
		return findAllResponse(response);
	}
	
	@Override
	@GetMapping("/getBySpecies/")
	public ResponseEntity<?> findAllBySpecies(@RequestBody Species species) {
		List<Offer> response = offerService.findAllBySpecies(species.getId());
		return findAllResponse(response);
	}
	
	@Override
	@GetMapping("/getBySpecies/{id}")
	public ResponseEntity<?> findAllBySpecies(@PathVariable Long id) {
		List<Offer> response = offerService.findAllBySpecies(id);
		return findAllResponse(response);
	}
	
	private ResponseEntity<?> findAllResponse(List<Offer> response) {
		if(response != null) {
			return new ResponseEntity<List<Offer>>(response, HttpStatus.OK);   
		}
		return new ResponseEntity<String>("Falha ao carregar ofertas", HttpStatus.NOT_FOUND);
	}

}
