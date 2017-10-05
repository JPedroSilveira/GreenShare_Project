package com.seedshare.entity.interfaces;

import com.seedshare.enumeration.PhotoType;

public interface PhotogenicEntity{
	
	public PhotoType getPhotoType();
	
	public void setHasImage(Boolean hasImage);
	
	public Boolean getHasImage();

	public Long getId();

}
