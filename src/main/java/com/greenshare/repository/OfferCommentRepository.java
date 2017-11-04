package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.offer.OfferComment;

/**
 * Repository Interface of {@link com.greenshare.entity.offer.OfferComment}
 * 
 * @author joao.silva
 */
public interface OfferCommentRepository extends CrudRepository<OfferComment, Long>{

}
