package com.seedshare.service.permission;

import org.springframework.http.ResponseEntity;

import com.seedshare.entity.user.Permission;
import com.seedshare.service.BasicService;

/**
 * Service interface of Permission
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public interface PermissionService extends BasicService<Permission, Long> {

	ResponseEntity<?> findAll();

}
