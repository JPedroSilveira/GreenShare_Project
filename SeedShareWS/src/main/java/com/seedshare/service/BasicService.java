package com.seedshare.service;

/**
 * Interface de service básica
 * @author joao.silva
 * @param <Entity>
 * @param <ID>
 */
public interface BasicService<Entity, ID> {
	Entity save(Entity e);
    
    void delete(Entity e);

    Entity findOne(ID id);
}
