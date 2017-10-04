package com.seedshare.service.species;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Species;
import com.seedshare.repository.SpeciesRepository;

/**
 * Implementation of Species Service interface
 * @author joao.silva
 */
@Service
public class SpeciesServiceImpl implements SpeciesService{

	@Autowired
    SpeciesRepository speciesRepository;
	
	@Override
	public Species save(Species species) {
		return speciesRepository.save(species);
	}

	@Override
	public void delete(Species e) {
		// TODO Auto-generated method stub
		
	}

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
			return speciesRepository.findOneByCommonName(commonName);
		}
		return null;
	}
}
