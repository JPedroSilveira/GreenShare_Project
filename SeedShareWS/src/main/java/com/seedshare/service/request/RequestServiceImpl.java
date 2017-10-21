package com.seedshare.service.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Offer;
import com.seedshare.entity.Request;
import com.seedshare.enumeration.OfferStatus;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.OfferRepository;
import com.seedshare.repository.RequestRepository;

/**
 * Implementation of {@link com.seedshare.service.request.RequestService}
 * interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class RequestServiceImpl extends IsHelper implements RequestService {

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	OfferRepository offerRepository;

	@Override
	public ResponseEntity<?> save(Request request) {
		if (isNotNull(request)) {
			Request newRequest = new Request(request.getAmount(), request.getOffer(), getCurrentUser());
			if (newRequest.isValid()) {
				newRequest = requestRepository.save(newRequest);
				return new ResponseEntity<Request>(newRequest, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newRequest.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Requisição não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			Request requestDB = requestRepository.findOne(id);
			if (isNotNull(requestDB) && requestDB.getUser().getId() == getCurrentUser().getId()) {
				requestRepository.delete(id);
				return new ResponseEntity<String>("Requisição deletada.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Requisição não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Request requestDB = requestRepository.findOne(id);
			if (isNotNull(requestDB)) {
				return new ResponseEntity<Request>(requestDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Requisição não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByOffer(Long id) {
		if(isNotNull(id)) {
			Offer offerDB = offerRepository.findOne(id);
			if(offerDB.getUser().getId() == getCurrentUserId()) {
				Iterable<Request> requestListDB = requestRepository.findAllByOffer(id);
				return new ResponseEntity<Iterable<Request>>(requestListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Oferta não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCurrentUser() {
		Iterable<Request> requestListDB = requestRepository.findAllByUser(getCurrentUser());
		return new ResponseEntity<Iterable<Request>>(requestListDB, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> acceptRequest(Long id) {
		if (isNotNull(id)) {
			Request requestDB = requestRepository.findOne(id);
			if (isNotNull(requestDB) && requestDB.getWasAccepted() == false) {
				Offer offerDB = offerRepository.findOne(requestDB.getOffer().getId());
				if(offerDB.getUser().getId() == getCurrentUserId()) {
					if(offerDB.getRemainingAmount() >= requestDB.getAmount()) {
						offerDB.setRemainingAmount(offerDB.getRemainingAmount() - requestDB.getAmount());
						if(offerDB.getRemainingAmount() == 0) {
							offerDB.setOfferStatus(OfferStatus.Closed);
						}
						requestDB.setWasAccepted(true);
						offerRepository.save(offerDB);
						requestRepository.save(requestDB);
						return new ResponseEntity<Request>(requestDB, HttpStatus.OK);
					}
					return new ResponseEntity<String>("Oferta não possui quantidade suficiente de produtos.", HttpStatus.PRECONDITION_FAILED);
				}
				return new ResponseEntity<String>("Oferta não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Requisição não encontrada ou já aceita.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("ID da requisição não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

}
