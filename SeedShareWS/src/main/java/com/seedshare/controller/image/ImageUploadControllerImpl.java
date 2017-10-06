package com.seedshare.controller.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Post;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;
import com.seedshare.entity.interfaces.PhotogenicEntity;
import com.seedshare.exception.DirectoryException;
import com.seedshare.helpers.ImageHelper;
import com.seedshare.helpers.UserUtils;
import com.seedshare.service.photo.PhotogenicServiceImpl;
import com.seedshare.service.post.PostServiceImpl;
import com.seedshare.service.species.SpeciesServiceImpl;

/**
 * @author joao.silva
 */
@RestController
public class ImageUploadControllerImpl extends UserUtils implements ImageUploadController{

	@Autowired
    PhotogenicServiceImpl photogenicService;
	
	@Autowired
    PostServiceImpl postService;
	
	@Autowired
    SpeciesServiceImpl speciesService;
	
    @Override
    @PostMapping("/user/image/upload")
    public ResponseEntity<String> uploadUserImage(@RequestParam("photo") MultipartFile multipartFile) {
    	User currentUser = getCurrentUser();
    	return saveImage(multipartFile, currentUser);
    }
    
    @Override
    @PostMapping("/post/image/upload")
    public ResponseEntity<String> uploadPostImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("postId") Long postId) {
    	Post post = postService.findOne(postId);
    	User currentUser = getCurrentUser();
    	if(post.getUser().getId() == currentUser.getId()) {
    		return saveImage(multipartFile, post);
    	}
    	return new ResponseEntity<String>("Usuário inválido.", HttpStatus.UNAUTHORIZED);
    }
    
    @Override
    @PostMapping("/flower_shop/image/upload")
    public ResponseEntity<String> uploadFlowerShopImage(@RequestParam("photo") MultipartFile multipartFile) {
    	User currentUser = getCurrentUser();
    	FlowerShop flowerShop = currentUser.getFlowerShop();
    	if(flowerShop != null) {
        	return saveImage(multipartFile, flowerShop);
    	}
    	return new ResponseEntity<String>("Usuário inválido.", HttpStatus.UNAUTHORIZED);
    }
    
    @Override
    @PostMapping("/species/image/upload")
    public ResponseEntity<String> uploadSpeciesImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("speciesId") Long speciesId) {
    	Species species = speciesService.findOne(speciesId);
    	if(species != null) {
        	return saveImage(multipartFile, species);
    	}
    	return new ResponseEntity<String>("Espécie inválida.", HttpStatus.UNAUTHORIZED);
    }
    
    @Override
    @GetMapping("/user/image/get")
	public ResponseEntity<?> getUserImage() {
    	User currentUser = getCurrentUser();
    	return getImage(currentUser);
	}
    
    @Override
    @GetMapping("/post/image/get/{postId}")
	public ResponseEntity<?> getPostImage(@PathVariable Long postId) {
    	Post post = postService.findOne(postId);
		return getImage(post);
	}
    
    @Override
    @GetMapping("/flower_shop/image/get/")
	public ResponseEntity<?> getFlowerShopImage() {
    	User currentUser = getCurrentUser();
    	FlowerShop flowerShop = currentUser.getFlowerShop();
    	if(flowerShop != null) {
        	return getImage(flowerShop);
    	}
    	return new ResponseEntity<String>("Usuário inválido.", HttpStatus.UNAUTHORIZED);
	}
    
    @Override
    @GetMapping("/species/image/get/{speciesId}")
	public ResponseEntity<?> getSpeciesImage(@PathVariable Long speciesId) {
    	Species species = speciesService.findOne(speciesId);
		return getImage(species);
	}
    
    private ResponseEntity<String> saveImage(MultipartFile multiPartFile, PhotogenicEntity photogenicEntity){
    	try {
    		ImageHelper imageHelper = new ImageHelper(photogenicEntity);
    		if(imageHelper.save(multiPartFile)) {
    			if(!photogenicEntity.getHasImage()){
        			photogenicEntity.setHasImage(true);
        			if(photogenicService.save(photogenicEntity) == null) {
        				return new ResponseEntity<String>("Erro ao salvar dados no banco.", HttpStatus.INTERNAL_SERVER_ERROR);
        			}
        		}
    			return new ResponseEntity<String>("Imagem salva com sucesso", HttpStatus.CREATED);
    		}
    		return new ResponseEntity<String>("Formato de imagem inválido ou arquivo muito grande", HttpStatus.NOT_ACCEPTABLE);
		} catch (DirectoryException e) {
	    	return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
	    	return new ResponseEntity<String>("Erro ao ler imagem.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    private ResponseEntity<?> getImage(PhotogenicEntity photogenicEntity){
		try {
			ImageHelper imageHelper = new ImageHelper(photogenicEntity);
			try {
				BufferedImage bufferedImage = imageHelper.getImage();
				if(bufferedImage != null) {
					return new ResponseEntity<BufferedImage>(bufferedImage, HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Imagem não encontrada", HttpStatus.NOT_FOUND);
				}
			} catch (IOException e) {
		    	return new ResponseEntity<String>("Erro ao ler imagem.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DirectoryException e) {
	    	return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
