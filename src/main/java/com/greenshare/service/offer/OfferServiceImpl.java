package com.greenshare.service.offer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.address.City;
import com.greenshare.entity.address.State;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.vegetable.SearchClass;
import com.greenshare.entity.vegetable.Species;
import com.greenshare.enumeration.OfferStatus;
import com.greenshare.exception.DirectoryException;
import com.greenshare.helpers.Base64MultpartFile;
import com.greenshare.helpers.ImageHelper;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.CityRepository;
import com.greenshare.repository.FlowerShopRepository;
import com.greenshare.repository.OfferCommentRepository;
import com.greenshare.repository.OfferRepository;
import com.greenshare.repository.SpeciesRepository;
import com.greenshare.repository.StateRepository;
import com.greenshare.repository.UserRepository;
import com.greenshare.service.image.ImageServiceImpl;

/**
 * Implementation of {@link com.greenshare.service.offer.OfferService} interface
 * 
 * @author joao.silva
 */
@Service
public class OfferServiceImpl extends IsHelper implements OfferService {

	@Autowired
	ImageServiceImpl imageService;
	
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
		if (isNotNull(offer)) {
			Species species = offer.getSpecies();
			if (isNotNull(species) && isNotNull(species.getId())) {
				species = speciesRepository.findOne(species.getId());
				if (isNotNull(species)) {
					Offer newOffer = new Offer(offer.getUnitPrice(), offer.getRemainingAmount(), getCurrentUser(),
							species, offer.getDescription());
					if (newOffer.isValid()) {
						newOffer = offerRepository.save(newOffer);
						if(isNotNull(offer.getImage())) {
							ImageHelper imageHelper;
							try {
								imageHelper = new ImageHelper(newOffer);
							} catch (DirectoryException e) {
								return new ResponseEntity<String>("Erro ao acessar diretório interno.", HttpStatus.INTERNAL_SERVER_ERROR);
							}
				    		byte[] decodedBytes = Base64.getDecoder().decode(offer.getImage());
				    		Base64MultpartFile multipartFile = new Base64MultpartFile(decodedBytes);
				    		try {
								if(imageHelper.save(multipartFile)) {
									if(!newOffer.getHasImage()){
										newOffer.setHasImage(true);
										if(isNull(imageService.save(newOffer))) {
											return new ResponseEntity<String>("Erro ao salvar dados no banco.", HttpStatus.INTERNAL_SERVER_ERROR);
										}
									}
								}
							} catch (IOException e) {
								return new ResponseEntity<String>("Erro ao salvar imagem no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
							}
						}
						return new ResponseEntity<Offer>(newOffer, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(newOffer.getValidationErrors(), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Espécie não pode ser nula.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Oferta não pode ser nula.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> search(Integer page, Integer size, SearchClass searchClass) {
		if(isNotNull(searchClass) && isNotNull(page) && isNotNull(size)){
			Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "insertionDate"));
			List<Offer> retorno = null;
			if(isNull(searchClass.rootDepth) || searchClass.rootDepth == 0) {
				searchClass.rootDepth = 50000;
			}
			if(isNull(searchClass.averageHeight) || searchClass.averageHeight == 0) {
				searchClass.averageHeight = 50000;
			}
			if(isNotNull(searchClass.species)) {
				Species species = searchClass.species;
				retorno = offerRepository.findAllBySpecies(species, pageable);
			}else {
				if(searchClass.hasFlower && searchClass.hasFruit) {
					retorno = offerRepository.findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamental(searchClass.growth, searchClass.soil, searchClass.climate, searchClass.isMedicinal, searchClass.attractBirds, searchClass.attractBees, searchClass.isOrnamental, searchClass.rootDepth, searchClass.averageHeight,  pageable);
				} else if(searchClass.hasFlower) {
					retorno = offerRepository.findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndSpeciesFruitIsNull(searchClass.growth, searchClass.soil, searchClass.climate, searchClass.isMedicinal, searchClass.attractBirds, searchClass.attractBees, searchClass.isOrnamental, searchClass.rootDepth, searchClass.averageHeight,  pageable);
				} else if(searchClass.hasFruit) {
					retorno = offerRepository.findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndSpeciesFlowerIsNull(searchClass.growth, searchClass.soil, searchClass.climate, searchClass.isMedicinal, searchClass.attractBirds, searchClass.attractBees, searchClass.isOrnamental, searchClass.rootDepth, searchClass.averageHeight,  pageable);
				} else {
					retorno = offerRepository.findAllBySpeciesGrowthInAndSpeciesSoilsInAndSpeciesClimatesInAndSpeciesIsMedicinalAndSpeciesAttractBirdsAndSpeciesAttractBeesAndSpeciesIsOrnamentalAndSpeciesFruitIsNullAndSpeciesFlowerIsNull(searchClass.growth, searchClass.soil, searchClass.climate, searchClass.isMedicinal, searchClass.attractBirds, searchClass.attractBees, searchClass.isOrnamental, searchClass.rootDepth, searchClass.averageHeight,  pageable);
				}
			}
			retorno.stream().filter(s -> s.getSpecies().getRootDepth() < searchClass.rootDepth && s.getSpecies().getAverageHeight() < searchClass.averageHeight);
			retorno.stream().forEach(offer -> {
				if(offer.getHasImage()) {
					try {
						ImageHelper ih = new ImageHelper(offer);
						offer.setImage(ih.getImage());
					} catch (DirectoryException e) {
					} catch (IOException e) {					
					} catch (JSONException e) {}
				}
				if(offer.getUser().getHasImage()) {
					try {
						ImageHelper ih = new ImageHelper(offer.getUser());
						offer.getUser().setImage(ih.getImage());
					} catch (DirectoryException e) {
					} catch (IOException e) {					
					} catch (JSONException e) {}
				}
			});
			return new ResponseEntity<List<Offer>>(retorno, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Objeto de pesquisa e/ou paginação não podem ser nulos.", HttpStatus.BAD_REQUEST);
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
		Pageable pageable = new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "insertionDate"));
		if (isNotNull(id)) {
			List<Offer> offerListDB = offerRepository.findAllByUser(id, pageable);
			if (isNotNull(offerListDB)) {
				return new ResponseEntity<List<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Offer> offerListDB = offerRepository.findAll();
		if (isNotNull(offerListDB)) {
			return new ResponseEntity<Iterable<Offer>>(offerListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> findAllByFlowerShop(Long id) {
		Pageable pageable = new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "insertionDate"));
		if (isNotNull(id)) {
			FlowerShop flowerShopDB = flowerShopRepository.findOne(id);
			if (isNotNull(flowerShopDB)) {
				List<Offer> offerListDB = offerRepository.findAllByUser(getCurrentUserId(),
						pageable);
				return new ResponseEntity<List<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Floricultura não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllBySpecies(Long id) {
		Pageable pageable = new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "insertionDate"));
		if (isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			if (isNotNull(speciesDB)) {
				List<Offer> offerListDB = offerRepository.findAllBySpecies(speciesDB, pageable);
				return new ResponseEntity<List<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByState(Long id) {
		Pageable pageable = new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "insertionDate"));
		if (isNotNull(id)) {
			State stateDB = stateRepository.findOne(id);
			if (isNotNull(stateDB)) {
				List<Offer> offerListDB = offerRepository.findAllByUserAddressCityState(stateDB.getId(),
						pageable);
				return new ResponseEntity<List<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Estado não encontrado.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCity(Long id) {
		Pageable pageable = new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "insertionDate"));
		if (isNotNull(id)) {
			City cityDB = cityRepository.findOne(id);
			if (isNotNull(cityDB)) {
				List<Offer> offerListDB = offerRepository.findAllByUserAddressCity(cityDB.getId(),
						pageable);
				return new ResponseEntity<List<Offer>>(offerListDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Cidade não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCurrentUser() {
		Pageable pageable = new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "insertionDate"));
		Long currentUserID = getCurrentUserId();
		if (isNotNull(currentUserID)) {
			List<Offer> offerListDB = offerRepository.findAllByUser(currentUserID, pageable);
			return new ResponseEntity<List<Offer>>(offerListDB, HttpStatus.OK);
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
