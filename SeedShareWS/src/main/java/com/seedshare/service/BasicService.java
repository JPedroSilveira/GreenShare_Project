package com.seedshare.service;

import org.springframework.http.ResponseEntity;

/**
 * @author joao.silva
 * @param <Entity>
 * @param <ID>
 */
public interface BasicService<Entity, ID> {
	ResponseEntity<?> save(Entity e);
    
    ResponseEntity<?> delete(ID id);

    ResponseEntity<?> findOne(ID id);
    
    ResponseEntity<?> update(Entity e);
}
