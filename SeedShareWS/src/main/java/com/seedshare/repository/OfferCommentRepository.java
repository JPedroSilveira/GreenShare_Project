package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.offer.OfferComment;

/**
 * Repository Interface of {@link com.seedshare.entity.offer.OfferComment}
 * 
 * @author joao.silva
 */
public interface OfferCommentRepository extends CrudRepository<OfferComment, Long>{

}
