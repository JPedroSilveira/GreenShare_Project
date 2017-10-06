package com.seedshare.service.species;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Species;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.SpeciesRepository;

/**
 * Implementation of Species Service interface
 * @author joao.silva
 */
@Service
public class SpeciesServiceImpl extends IsHelper implements SpeciesService{

	@Autowired
    SpeciesRepository speciesRepository;

	@Override
	public Species findOne(Long id) {
		if(id != null) {
			return speciesRepository.findOne(id);
		}
		return null;
	}

	@Override
	public Species findOneByCommonName(String commonName) {
		if(commonName != null) {
			List<Species> speciesDB = speciesRepository.findOneByCommonName(commonName);
			Species response = speciesDB.stream().filter(species -> species.getIsApproved()).findFirst().orElse(null);
			return response;
		}
		return null;
	}
	
	@Override
	public Species findOneByScientificName(String scientificName) {
		if(isNotNull(scientificName)) {
			List<Species> speciesDB = speciesRepository.findOneByScientificName(scientificName);
			Species response = speciesDB.stream().filter(species -> species.getIsApproved()).findFirst().orElse(null);
			return response;
		}
		return null;
	}
}
