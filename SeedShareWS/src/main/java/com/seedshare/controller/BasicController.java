package com.seedshare.controller;

import org.springframework.http.ResponseEntity;

/**
 * @author joao.silva
 * @param <Entity>
 * @param <ID>
 */
public interface BasicController<Entity,ID> {
	
	ResponseEntity<?> save(Entity e);
    
	ResponseEntity<?> delete(Entity e);

	ResponseEntity<?> findOne(ID id);
}
