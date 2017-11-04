package com.greenshare.entity.interfaces;

import com.greenshare.enumeration.PhotoType;

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
