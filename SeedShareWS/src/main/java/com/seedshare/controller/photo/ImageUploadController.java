package com.seedshare.controller.photo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author joao.silva
 */
public interface ImageUploadController{
		
	ResponseEntity<String> uploadProfileImage(MultipartFile file);
	
    ResponseEntity<String> uploadPostImage(MultipartFile multipartFile, Long postId);
	
	ResponseEntity<?> getProfileImage();
	
	ResponseEntity<?> getPostImage(Long idPost);
}
