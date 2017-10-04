package com.seedshare.entity.interfaces;

import com.seedshare.enumeration.PhotoType;

public interface PhotogenicEntity extends BasicEntity{
	
	public PhotoType getPhotoType();
	
	public void setHasImage(Boolean hasImage);
	
	public Boolean getHasImage();

}
