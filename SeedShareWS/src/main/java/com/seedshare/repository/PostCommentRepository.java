package com.seedshare.repository;

import org.springframework.data.repository.CrudRepository;

import com.seedshare.entity.post.PostComment;

/**
 * Repository Interface of {@link com.seedshare.entity.post.PostComment}
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
public interface PostCommentRepository extends CrudRepository<PostComment, Long>{

	Iterable<PostComment> findAllByPost(Long id);

}
