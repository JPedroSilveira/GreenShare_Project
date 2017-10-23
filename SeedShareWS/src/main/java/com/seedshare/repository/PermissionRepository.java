package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.user.Permission;

/**
 * Repository Interface of {@link com.seedshare.entity.user.Permission}
 * 
 * @author joao.silva
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {

}
