package com.seedshare.service.image;

import com.seedshare.entity.interfaces.PhotogenicEntity;

/**
 * Service interface for save images on system
 * 
 * @author joao.silva
 */
public interface ImageService {
	PhotogenicEntity save(PhotogenicEntity photogenicEntity);
}
