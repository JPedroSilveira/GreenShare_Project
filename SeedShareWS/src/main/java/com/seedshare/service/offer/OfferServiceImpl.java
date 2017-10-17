package com.seedshare.service.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.City;
import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Offer;
import com.seedshare.entity.OfferComment;
import com.seedshare.entity.Species;
import com.seedshare.entity.State;
import com.seedshare.enumeration.OfferStatus;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.CityRepository;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.OfferCommentRepository;
import com.seedshare.repository.OfferRepository;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.StateRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of {@link com.seedshare.service.offer.OfferService} interface
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Service
public class OfferServiceImpl extends IsHelper implements OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	FlowerShopRepository flowerShopRepository;

	@Autowired
	SpeciesRepository speciesRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	OfferCommentRepository offerCommentRepository;

	@Override
	public ResponseEntity<?> save(Offer offer) {
		if(isNotNull(offer)) {
			Offer newOffer = new Offer(offer.getUnitPrice(), offer.getAmount(), getCurrentUser(), offer.getSpecies(),
					offer.getDescription(), getCurrentUser().getFlowerShop());
			if (newOffer.isValid()) {
				newOffer = offerRepository.save(offer);
				newOffer.getUser().clearPrivateData();
				return new ResponseEntity<Offer>(newOffer, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(newOffer.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Oferta não pode ser nula.", HttpStatus.BAD_REQUEST);		
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			Offer offerToDelete = offerRepository.findOne(id);
			if (isNotNull(offerToDelete) && offerToDelete.getUser().getId() == getCurrentUser().getId()) {
				offerToDelete.setOfferStatus(OfferStatus.Closed);
				offerRepository.save(offerToDelete);
				return new ResponseEntity<String>("Oferta encerrada.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Oferta não pertencente ao usuário atual.",
					HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Offer offerDB = offerRepository.findOne(id);
			if (isNotNull(offerDB)) {
				offerDB.getUser().clearPrivateData();
				return new ResponseEntity<Offer>(offerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nível de crescimento não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByUser(Long id) {
		if (isNotNull(id)) {
			Iterable<Offer> offerListDB = offerRepository.findAllByUser(id);
			if (isNotNull(offerListDB)) {
				offerListDB.forEach(offer -> offer.getUser().clearPrivateData());
				return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByFlowerShop(Long id) {
		if (isNotNull(id)) {
			FlowerShop flowerShopDB = flowerShopRepository.findOne(id);
			if (isNotNull(flowerShopDB)) {
				Iterable<Offer> offerListDB = offerRepository.findAllByFlowerShop(flowerShopDB.getId());
				offerListDB.forEach(offer -> offer.getUser().clearPrivateData());
				return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Floricultura não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllBySpecies(Long id) {
		if (isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			if (isNotNull(speciesDB)) {
				Iterable<Offer> offerListDB = offerRepository.findAllBySpecies(speciesDB.getId());
				offerListDB.forEach(offer -> offer.getUser().clearPrivateData());
				return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findAllByState(Long id) {
		if (isNotNull(id)) {
			State stateDB = stateRepository.findOne(id);
			if (isNotNull(stateDB)) {
				Iterable<Offer> offerListDB = offerRepository.findAllByState(stateDB.getId());
				offerListDB.forEach(offer -> offer.getUser().clearPrivateData());
				return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Estado não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findAllByCity(Long id) {
		if (isNotNull(id)) {
			City cityDB = cityRepository.findOne(id);
			if (isNotNull(cityDB)) {
				Iterable<Offer> offerListDB = offerRepository.findAllByCity(cityDB.getId());
				offerListDB.forEach(offer -> offer.getUser().clearPrivateData());
				return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> addComment(Long id, String text) {
		if (isNotNull(id) && isNotNull(text)) {
			Offer offerDB = offerRepository.findOne(id);
			if (isNotNull(offerDB)) {
				OfferComment newOfferComment = new OfferComment(text, getCurrentUser(), offerDB);
				if(newOfferComment.isValid()) {
					newOfferComment = offerCommentRepository.save(newOfferComment);
					newOfferComment.getUser().clearPrivateData();
					return new ResponseEntity<OfferComment>(newOfferComment, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(offerDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> deleteComment(Long id) {
		if (isNotNull(id)) {
			OfferComment offerCommentDB  = offerCommentRepository.findOne(id);
			if(offerCommentDB.getUser().getId() == getCurrentUser().getId()) {
				offerCommentRepository.delete(id);
				return new ResponseEntity<String>("Comentário deletado", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Comentário não pertence ao usuário atual.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
