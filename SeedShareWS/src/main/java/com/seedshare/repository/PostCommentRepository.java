package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.PostComment;

/**
 * Repository Interface for PostComment
 * 
 * @author joao.silva
 */
public interface PostCommentRepository extends CrudRepository<PostComment, Long>{

}
