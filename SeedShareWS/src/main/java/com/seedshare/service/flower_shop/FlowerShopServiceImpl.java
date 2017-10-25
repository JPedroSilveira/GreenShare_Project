package com.seedshare.service.flower_shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.user.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.UserRepository;

/**
 * Service implementation of
 * {@link com.seedshare.service.flower_shop.FlowerShopService}
 * 
 * @author joao.silva
 */
@Service
public class FlowerShopServiceImpl extends IsHelper implements FlowerShopService {

	@Autowired
	FlowerShopRepository flowerShopRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseEntity<?> save(FlowerShop flowerShop) {
		User currentUser = getCurrentUser();
		if (currentUser.getIsLegalPerson()) {
			FlowerShop flowerShopDB = flowerShopRepository.findOneByUser(currentUser);
			if (isNull(flowerShopDB)) {
				flowerShopDB = flowerShopRepository.findOneByCnpj(flowerShop.getCnpj());
				if (isNull(flowerShopDB) || !flowerShopDB.getEnabled()) {
					FlowerShop newFlowerShop = new FlowerShop(flowerShop.getCnpj(), flowerShop.getDescription(),
							currentUser);
					if (newFlowerShop.isValid()) {
						newFlowerShop = flowerShopRepository.save(newFlowerShop);
						return new ResponseEntity<FlowerShop>(newFlowerShop, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(newFlowerShop.getValidationErrors(),
							HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("Floricultura ativa já cadastrada para o usuário.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Para cadastrar uma floricultura é necessário ser pessoa jurídica.",
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(FlowerShop flowerShop) {
		User currentUser = getCurrentUser();
		FlowerShop flowerShopDB = flowerShopRepository.findOneByUser(currentUser);
		if (isNotNull(flowerShopDB) && flowerShopDB.getEnabled()) {
			flowerShopDB.setDescription(flowerShop.getDescription());
			flowerShopDB.setName(flowerShop.getName());
			if (flowerShopDB.isValid()) {
				flowerShopDB = flowerShopRepository.save(flowerShopDB);
				return new ResponseEntity<FlowerShop>(flowerShopDB, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(flowerShopDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Floricultura inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			FlowerShop flowerShop = flowerShopRepository.findOneByUser(getCurrentUser());
			if (flowerShop.getId() == id) {
				flowerShop.disable();
				flowerShopRepository.save(flowerShop);
				return new ResponseEntity<String>("Floricultura removida com sucesso.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Floricultura não associada ao usuário atual.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			FlowerShop flowerDB = flowerShopRepository.findOne(id);
			if (isNotNull(flowerDB)) {
				return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOneByCnpj(String cnpj) {
		if (isNotNull(cnpj)) {
			FlowerShop flowerDB = flowerShopRepository.findOneByCnpj(cnpj);
			if (isNotNull(flowerDB)) {
				return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOneByCurrentUser() {
		User currentUser = getCurrentUser();
		if (isNotNull(currentUser) && currentUser.getIsLegalPerson()) {
			FlowerShop flowerDB = flowerShopRepository.findOneByUser(getCurrentUser());
			if (isNotNull(flowerDB)) {
				return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOneByUser(Long id) {
		if (isNotNull(id)) {
			User userDB = userRepository.findOne(id);
			if (isNotNull(userDB) && userDB.getIsLegalPerson()) {
				FlowerShop flowerDB = flowerShopRepository.findOneByUser(userDB);
				if (isNotNull(flowerDB)) {
					return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
				}
				return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<String>("Usuário pode possuir floricultura.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByState(Long id) {
		if (isNotNull(id)) {
			Iterable<FlowerShop> flowersDB = flowerShopRepository.findAllByAddressCityStateAndIsActiveTrue(id);
			return new ResponseEntity<Iterable<FlowerShop>>(flowersDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCity(Long id) {
		if (isNotNull(id)) {
			Iterable<FlowerShop> flowersDB = flowerShopRepository.findAllByAddressCityAndIsActiveTrue(id);
			return new ResponseEntity<Iterable<FlowerShop>>(flowersDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
