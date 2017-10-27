package com.seedshare.entity.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface BasicEntity<Entity> {

	Long getId();

	@JsonIgnore
	boolean isValid();
	
	void update(Entity e);

}
