package com.greenshare.controller.request;

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

import com.greenshare.entity.offer.Request;
import com.greenshare.service.request.RequestServiceImpl;

/**
 * Controller class of {@link com.greenshare.entity.offer.Request}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@RestController
@RequestMapping("/request/")
public class RequestControllerImpl implements RequestController{

	@Autowired
	RequestServiceImpl requestService;

	@Override
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Request request) {
		return requestService.save(request);
	}

	@Override
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return requestService.delete(id);
	}

	@Override
	@GetMapping("{id}")
	public ResponseEntity<?> findOne(@PathVariable Long id) {
		return requestService.findOne(id);
	}

	@Override
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Request request) {
		return requestService.update(request);
	}

	@Override
	@GetMapping("offer/{id}")
	public ResponseEntity<?> findAllByOffer(@PathVariable Long id) {
		return requestService.findAllByOffer(id);
	}

	@Override
	@GetMapping("current_user/")
	public ResponseEntity<?> findAllByCurrentUser() {
		return requestService.findAllByCurrentUser();
	}

	@Override
	@GetMapping("accept_request/{id}")
	public ResponseEntity<?> acceptRequest(@PathVariable Long id) {
		return requestService.acceptRequest(id);
	}
	
}
