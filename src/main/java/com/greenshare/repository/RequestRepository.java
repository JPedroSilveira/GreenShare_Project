package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.offer.Request;
import com.greenshare.entity.user.User;

/**
 * Repository Interface of {@link com.greenshare.entity.offer.Request}
 * 
 * @author joao.silva
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

	Iterable<Request> findAllByUser(User user);

	Iterable<Request> findAllByOffer(Long id);

}
