package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.user.Permission;

/**
 * Repository Interface of {@link com.greenshare.entity.user.Permission}
 * 
 * @author joao.silva
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {

}
