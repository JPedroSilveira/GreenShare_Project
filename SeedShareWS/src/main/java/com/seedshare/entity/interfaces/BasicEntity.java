package com.seedshare.entity.interfaces;

public interface BasicEntity<Entity> {

	Long getId();

	boolean isValid();
	
	void update(Entity e);

}
