package com.greenshare.service.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.address.Address;
import com.greenshare.entity.address.City;
import com.greenshare.entity.address.State;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.user.User;
import com.greenshare.entity.vegetable.Species;
import com.greenshare.enumeration.OfferStatus;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.CityRepository;
import com.greenshare.repository.FlowerShopRepository;
import com.greenshare.repository.OfferCommentRepository;
import com.greenshare.repository.OfferRepository;
import com.greenshare.repository.SpeciesRepository;
import com.greenshare.repository.StateRepository;
import com.greenshare.repository.UserRepository;

/**
 * Implementation of {@link com.greenshare.service.offer.OfferService} interface
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
		User currentUser = getCurrentUser();
		if (isNotNull(currentUser) && currentUser.hasAddress()) {
			if (isNotNull(offer)) {
				Species species = offer.getSpecies();
				Address offerAddress = currentUser.getAddressForOffer();
				if (isNotNull(species.getId())) {
					species = speciesRepository.findOne(species.getId());
					if (isNotNull(species)) {
						Offer newOffer = new Offer(offer.getUnitPrice(), offer.getRemainingAmount(), getCurrentUser(),
								species, offer.getDescription(), getCurrentUser().getFlowerShop(), offerAddress, offer.getProductAge());
						if (newOffer.isValid()) {
							newOffer = offerRepository.save(offer);
							return new ResponseEntity<Offer>(newOffer, HttpStatus.OK);
						}
						return new ResponseEntity<List<String>>(newOffer.getValidationErrors(), HttpStatus.BAD_REQUEST);
					}
					return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<String>("Espécie não possui identificação válida.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Oferta não pode ser nula.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Necessário endereço cadastrado.", HttpStatus.NO_CONTENT);
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
				return new ResponseEntity<Offer>(offerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nível de crescimento não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByUser(Long id) {
		if (isNotNull(id)) {
			Iterable<Offer> offerListDB = offerRepository.findAllByUserAndOfferStatus(id, 0);
			if (isNotNull(offerListDB)) {
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
				Iterable<Offer> offerListDB = offerRepository.findAllByFlowerShopAndOfferStatus(flowerShopDB.getId(),
						OfferStatus.Active.getValue());
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
				Iterable<Offer> offerListDB = offerRepository.findAllBySpeciesAndOfferStatus(speciesDB.getId(),
						OfferStatus.Active.getValue());
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
				Iterable<Offer> offerListDB = offerRepository.findAllByAddressCityStateAndOfferStatus(stateDB.getId(),
						OfferStatus.Active.getValue());
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
				Iterable<Offer> offerListDB = offerRepository.findAllByAddressCityAndOfferStatus(cityDB.getId(),
						OfferStatus.Active.getValue());
				return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCurrentUser() {
		Long currentUserID = getCurrentUserId();
		if (isNotNull(currentUserID)) {
			Iterable<Offer> offerListDB = offerRepository.findAllByUser(currentUserID);
			return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nenhum usuário logado.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Offer offer) {
		if (isNotNull(offer)) {
			Offer offerDB = offerRepository.findOne(offer.getId());
			if (isNotNull(offerDB) && offerDB.getOfferStatus() == OfferStatus.Active.getValue()) {
				if (offerDB.getUser().getId() == getCurrentUserId()) {
					offerDB.update(offer);
					if (offerDB.isValid()) {
						offerDB = offerRepository.save(offerDB);
						return new ResponseEntity<Offer>(offerDB, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(offerDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Oferta não pertence ao usuário logado.", HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>("Oferta não encontrada ou inativa.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Oferta não pode ser nula.", HttpStatus.BAD_REQUEST);
	}
}
