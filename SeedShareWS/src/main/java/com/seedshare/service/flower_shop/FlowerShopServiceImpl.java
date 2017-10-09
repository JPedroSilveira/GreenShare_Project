package com.seedshare.service.flower_shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of FlowerShop Service interface
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
			FlowerShop flowerShopDB = flowerShopRepository.findByUser(currentUser);
			if (isNull(flowerShopDB)) {
				flowerShopDB = flowerShopRepository.findByCnpj(flowerShop.getCnpj());
				if (isNull(flowerShopDB) || !flowerShopDB.getIsActive()) {
					FlowerShop newFlowerShop = new FlowerShop(flowerShop.getCnpj(), flowerShop.getDescription(),
							currentUser);
					if (newFlowerShop.isValid()) {
						newFlowerShop = flowerShopRepository.save(newFlowerShop);
						newFlowerShop.getUser().cleanPrivateDate();
						return new ResponseEntity<FlowerShop>(newFlowerShop, HttpStatus.OK);
					}
					return new ResponseEntity<List<String>>(newFlowerShop.getValidationErrors(),
							HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("Floricultura já cadastrada para o usuário.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Para cadastrar uma floricultura é necessário ser pessoa física.",
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(FlowerShop flowerShop) {
		User currentUser = getCurrentUser();
		FlowerShop flowerShopDB = flowerShopRepository.findByUser(currentUser);
		if (isNotNull(flowerShopDB) && flowerShopDB.getIsActive()) {
			flowerShopDB.setDescription(flowerShop.getDescription());
			flowerShopDB.setName(flowerShop.getName());
			if (flowerShopDB.isValid()) {
				flowerShopDB = flowerShopRepository.save(flowerShopDB);
				flowerShopDB.getUser().cleanPrivateDate();
				return new ResponseEntity<FlowerShop>(flowerShopDB, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(flowerShopDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Floricultura inválida.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			FlowerShop flowerShop = flowerShopRepository.findOne(id);
			if (flowerShop.getUser().getId() == getCurrentUserId()) {
				flowerShop.setIsActive(false);
				flowerShopRepository.save(flowerShop);
				return new ResponseEntity<String>("Floricultura desativada com sucesso.", HttpStatus.OK);
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
				flowerDB.getUser().cleanPrivateDate();
				return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByCnpj(String cnpj) {
		if (isNotNull(cnpj)) {
			FlowerShop flowerDB = flowerShopRepository.findByCnpj(cnpj);
			if (isNotNull(flowerDB)) {
				flowerDB.getUser().cleanPrivateDate();
				return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByCurrentUser() {
		User currentUser = getCurrentUser();
		if (isNotNull(currentUser) && currentUser.getIsLegalPerson()) {
			FlowerShop flowerDB = flowerShopRepository.findByUser(getCurrentUser());
			if (isNotNull(flowerDB)) {
				flowerDB.getUser().cleanPrivateDate();
				return new ResponseEntity<FlowerShop>(flowerDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Nenhuma floricultura encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findByUser(Long id) {
		if (isNotNull(id)) {
			User userDB = userRepository.findOne(id);
			if (isNotNull(userDB) && userDB.getIsLegalPerson()) {
				FlowerShop flowerDB = flowerShopRepository.findByUser(userDB);
				if (isNotNull(flowerDB)) {
					flowerDB.getUser().cleanPrivateDate();
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
			Iterable<FlowerShop> flowersDB = flowerShopRepository.findAllByState(id);
			return new ResponseEntity<Iterable<FlowerShop>>(flowersDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByCity(Long id) {
		if (isNotNull(id)) {
			Iterable<FlowerShop> flowersDB = flowerShopRepository.findAllByCity(id);
			return new ResponseEntity<Iterable<FlowerShop>>(flowersDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
