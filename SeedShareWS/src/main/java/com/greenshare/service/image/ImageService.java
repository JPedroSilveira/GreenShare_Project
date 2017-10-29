package com.greenshare.service.image;

import com.greenshare.entity.interfaces.PhotogenicEntity;

/**
 * Service interface for save images on system
 * 
 * @author joao.silva
 */
public interface ImageService {
	
	PhotogenicEntity save(PhotogenicEntity photogenicEntity);
	
}
