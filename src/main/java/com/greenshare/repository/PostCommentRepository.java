package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenshare.entity.post.PostComment;

/**
 * Repository Interface of {@link com.greenshare.entity.post.PostComment}
 * 
 * @author joao.silva
 */
@Repository
public interface PostCommentRepository extends CrudRepository<PostComment, Long>{

	Iterable<PostComment> findAllByPost(Long id);

}
