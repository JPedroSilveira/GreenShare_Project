package com.seedshare.service.species;

import com.seedshare.entity.Species;

/**
 * Service interface of Species
 * @author joao.silva
 */
public interface SpeciesService{

	Species findOneByCommonName(String commonName);

	Species findOne(Long id);

	Species findOneByScientificName(String scientificName);

}
