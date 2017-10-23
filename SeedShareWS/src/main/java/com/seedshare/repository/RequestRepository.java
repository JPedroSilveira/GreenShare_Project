package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seedshare.entity.offer.Request;
import com.seedshare.entity.user.User;

/**
 * Repository Interface of {@link com.seedshare.entity.offer.Request}
 * 
 * @author joao.silva
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

	Iterable<Request> findAllByUser(User user);

	Iterable<Request> findAllByOffer(Long id);

}
