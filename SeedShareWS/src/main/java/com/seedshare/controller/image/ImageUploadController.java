package com.seedshare.controller.image;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author joao.silva
 */
public interface ImageUploadController{
			
    ResponseEntity<String> uploadPostImage(MultipartFile multipartFile, Long postId);
	
	ResponseEntity<?> getPostImage(Long idPost);

	ResponseEntity<String> uploadFlowerShopImage(MultipartFile multipartFile);

	ResponseEntity<String> uploadUserImage(MultipartFile multipartFile);

	ResponseEntity<String> uploadSpeciesImage(MultipartFile multipartFile, Long speciesId);

	ResponseEntity<?> getSpeciesImage(Long speciesId);

	ResponseEntity<?> getFlowerShopImage();

	ResponseEntity<?> getUserImage();
}
