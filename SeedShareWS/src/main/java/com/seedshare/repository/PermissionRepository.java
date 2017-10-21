package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Permission;

/**
 * Repository Interface of {@link com.seedshare.entity.Permission}
 * 
 * @author joao.silva
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {

}
