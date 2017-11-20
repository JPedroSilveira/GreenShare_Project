package com.greenshare.entity.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Basic interface for entities
 * 
 * @author joao.silva
 */
public interface BasicEntity<Entity> {

	Long getId();

	@JsonIgnore
	boolean isValid();
	
	void update(Entity e);

}
