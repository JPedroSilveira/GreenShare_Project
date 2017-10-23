package com.seedshare.entity.interfaces;

import com.seedshare.enumeration.PhotoType;

/**
 * Interface for entities with images to upload
 * 
 * @author joao.silva
 */
public interface PhotogenicEntity {

	public PhotoType getPhotoType();

	public void setHasImage(Boolean hasImage);

	public Boolean getHasImage();

	public Long getId();

}
