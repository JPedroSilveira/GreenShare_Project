package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.Request;

/**
 * Repository Class for Request
 * @author joao.silva
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long>{

}
