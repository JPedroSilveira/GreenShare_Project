package com.greenshare.controller.image;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller implementation for upload images using {@link com.greenshare.service.image.ImageService}
 * 
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

	ResponseEntity<String> uploadFruitImage(MultipartFile multipartFile, Long speciesId);

	ResponseEntity<String> uploadFlowerImage(MultipartFile multipartFile, Long speciesId);

	ResponseEntity<String> uploadAchievementImage(MultipartFile multipartFile, Long speciesId);

	ResponseEntity<String> uploadOfferImage(MultipartFile multipartFile, Long speciesId);

	ResponseEntity<?> getAchievementImage(Long achievementId);

	ResponseEntity<?> getFlowerImage(Long flowerId);

	ResponseEntity<?> getFruitImage(Long fruitId);

	ResponseEntity<?> getOfferImage(Long offerId);
}
