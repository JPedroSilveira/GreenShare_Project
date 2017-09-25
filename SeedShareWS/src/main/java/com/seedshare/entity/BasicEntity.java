package com.seedshare.entity;


public abstract class BasicEntity{
	public Boolean isNullOrEmpty(String string) {
	    return string == null || string.isEmpty();
	}
}
