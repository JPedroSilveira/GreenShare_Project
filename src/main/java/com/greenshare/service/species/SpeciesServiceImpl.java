package com.greenshare.service.species;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.Month;
import com.greenshare.entity.vegetable.Climate;
import com.greenshare.entity.vegetable.Flower;
import com.greenshare.entity.vegetable.Fruit;
import com.greenshare.entity.vegetable.Growth;
import com.greenshare.entity.vegetable.Soil;
import com.greenshare.entity.vegetable.Species;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.ClimateRepository;
import com.greenshare.repository.GrowthRepository;
import com.greenshare.repository.MonthRepository;
import com.greenshare.repository.SoilRepository;
import com.greenshare.repository.SpeciesRepository;

/**
 * Implementation of Species Service interface
 * 
 * @author joao.silva
 */
@Service
public class SpeciesServiceImpl extends IsHelper implements SpeciesService{

	@Autowired
    SpeciesRepository speciesRepository;

	@Autowired
	MonthRepository monthRepository;
	
	@Autowired
	GrowthRepository growthRepository;
	
	@Autowired
	SoilRepository soilRepository;
	
	@Autowired
	ClimateRepository climateRepository;

	@Override
	public ResponseEntity<?> save(Species species) {
		if (isNotNull(species)) {
			Fruit newFruit = species.getFruit();
			Flower newFlower = species.getFlower();
			List<Soil> soilList = species.getSoils();
			List<Climate> climateList = species.getClimates();
			Growth growth = species.getGrowth();
			if (isNotNull(newFruit)) {
				List<Month> MonthListDB = monthRepository.findAllByNumberIn(newFruit.getMonthNumbers());
				if(newFruit.getMonthNumbers().size() != MonthListDB.size()) {
					return new ResponseEntity<String>("Meses de frutificação passados inválidos.", HttpStatus.BAD_REQUEST);
				}
				newFruit = new Fruit(newFruit.getFaunaConsumption(), newFruit.getHumanConsumption(), MonthListDB,
						newFruit.getDescription(), newFruit.getName());
			}
			if (isNotNull(newFlower)) {
				List<Month> MonthListDB = monthRepository.findAllByNumberIn(newFlower.getMonthNumbers());
				if(newFruit.getMonthNumbers().size() != MonthListDB.size()) {
					return new ResponseEntity<String>("Meses de floração passados inválidos.", HttpStatus.BAD_REQUEST);
				}
				newFlower = new Flower(newFlower.getIsAromatic(), newFlower.getDescription(), newFlower.getName(),
						newFlower.getSpecies(), newFlower.getColors(), MonthListDB);
			}
			if(isNotNull(growth)) {
				growth = growthRepository.findOne(growth.getId());
				if(isNull(growth)) {
					return new ResponseEntity<String>("Crescimento inválido.", HttpStatus.BAD_REQUEST);
				}
			}
			List<Long> idList = new ArrayList<Long>();
			soilList.stream().map(Soil::getId).forEach(idList::add);
			soilList = soilRepository.findAllById(idList);
			if(idList.size() != soilList.size()) {
				return new ResponseEntity<String>("Solos passados inválidos.", HttpStatus.BAD_REQUEST);
			}
			climateList.stream().map(Climate::getId).forEach(idList::add);
			climateList = climateRepository.findAllById(idList);
			if(idList.size() != climateList.size()) {
				return new ResponseEntity<String>("Climas passados inválidos.", HttpStatus.BAD_REQUEST);
			}
			species = new Species(species.getAttractBirds(), species.getDescription(),
					species.getCultivationGuide(), species.getIsMedicinal(), species.getAttractBees(),
					species.getScientificName(), species.getCommonName(), species.getIsOrnamental(),
					species.getAverageHeight(), species.getGrowth(), species.getRootDepth(),
					species.getClimates(), species.getSoils(), newFlower, newFruit);
			if (species.isValid()) {
				species = speciesRepository.save(species);
				return new ResponseEntity<Species>(species, HttpStatus.OK);
			}
			return new ResponseEntity<List<String>>(species.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Espécie não pode ser nula.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findOne(Long id) {
		if(isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			return isNotNull(speciesDB) ? new ResponseEntity<Species>(speciesDB, HttpStatus.OK)
					: new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findAll(){
		Iterable<Species> specieList = speciesRepository.findAll();
		return new ResponseEntity<Iterable<Species>>(specieList, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> delete(Long id) {
		if(isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			speciesDB.disable();
			speciesRepository.save(speciesDB);
			return new ResponseEntity<String>("Espécie desativada.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOneByCommonName(String commonName) {
		if(isNotNull(commonName)) {
			Iterable<Species> speciesListDB = speciesRepository.findOneByCommonNameAndEnabledTrue(commonName);
			return new ResponseEntity<Iterable<Species>>(speciesListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nome da espécie não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<?> findOneByScientificName(String scientificName) {
		if(isNotNull(scientificName)) {
			Iterable<Species> speciesListDB = speciesRepository.findOneByScientificNameAndEnabledTrue(scientificName);
			return new ResponseEntity<Iterable<Species>>(speciesListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Nome científico da espécie não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> enable(Long id) {
		if(isNotNull(id)) {
			Species speciesDB = speciesRepository.findOne(id);
			if(isNotNull(speciesDB)) {
				speciesDB.enable();
				speciesDB = speciesRepository.save(speciesDB);
				return new ResponseEntity<Species>(speciesDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> update(Species species) {
		if (isNotNull(species)) {
			Species speciesDB = speciesRepository.findOne(species.getId());
			if (isNotNull(speciesDB)) {
				speciesDB.update(species);
				if (speciesDB.isValid()) {
					speciesDB = speciesRepository.save(speciesDB);
					return new ResponseEntity<Species>(speciesDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(speciesDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Espécie não pode ser nula.", HttpStatus.BAD_REQUEST);
	}
}
