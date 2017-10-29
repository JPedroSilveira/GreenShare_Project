package com.greenshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.greenshare.entity.post.PostComment;

/**
 * Repository Interface of {@link com.greenshare.entity.post.PostComment}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface PostCommentRepository extends CrudRepository<PostComment, Long>{

	Iterable<PostComment> findAllByPost(Long id);

}
