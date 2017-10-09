package com.seedshare.controller;

import org.springframework.http.ResponseEntity;

/**
 * @author joao.silva
 * @param <Entity>
 * @param <ID>
 */
public interface BasicController<Entity, ID> {

	ResponseEntity<?> save(Entity e);

	ResponseEntity<?> delete(ID id);

	ResponseEntity<?> findOne(ID id);
}
