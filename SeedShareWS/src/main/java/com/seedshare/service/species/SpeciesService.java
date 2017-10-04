package com.seedshare.service.species;

import com.seedshare.entity.Species;
import com.seedshare.service.BasicService;

/**
 * Service interface of Species
 * @author joao.silva
 */
public interface SpeciesService extends BasicService<Species,Long>{

	Species findOneByCommonName(String commonName);

}
