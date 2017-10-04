package com.seedshare.entity.abstracts;

import com.seedshare.entity.interfaces.BasicEntity;

public abstract class AbstractEntity implements BasicEntity{
	
	public Boolean isNullOrEmpty(String string) {
	    return string == null || string.isEmpty();
	}
	
}
