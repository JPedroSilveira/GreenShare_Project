package com.seedshare.controller.photo;

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

import com.seedshare.UserUtils;
import com.seedshare.entity.Post;
import com.seedshare.entity.User;
import com.seedshare.helpers.ImageHelper;
import com.seedshare.service.post.PostServiceImpl;
import com.seedshare.service.user.UserServiceImpl;

/**
 * @author joao.silva
 */
@RestController
public class ImageUploadControllerImpl extends UserUtils implements ImageUploadController{

	@Autowired
    UserServiceImpl usuarioService;
	
	@Autowired
	PostServiceImpl postService;
    
    @Override
    @PostMapping("/user/image/upload")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("photo") MultipartFile multipartFile) {
    	User currentUser = getCurrentUser();
    	Boolean imageSaved = saveImage(multipartFile, null, currentUser);
    	return returnMessageImageSaved(imageSaved);
    }
    
    @Override
    @PostMapping("/post/image/upload")
    public ResponseEntity<String> uploadPostImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("postId") Long postId) {
    	Post post = postService.findOne(postId);
    	User currentUser = getCurrentUser();
    	if(post.getUser().getId() == currentUser.getId()) {
    		Boolean imageSaved = saveImage(multipartFile, post, null);
        	return returnMessageImageSaved(imageSaved);
    	}
    	return new ResponseEntity<String>("Usuário inválido", HttpStatus.UNAUTHORIZED);
    }
    
    @Override
    @GetMapping("/user/image/get")
	public ResponseEntity<?> getProfileImage() {
    	User currentUser = getCurrentUser();
    	BufferedImage image;
		try {
			image = getImage(null,currentUser);
		} catch (IOException e) {
			image = null;
		} 
    	if(image != null) {
        	return new ResponseEntity<BufferedImage>(image, HttpStatus.OK);
    	}
    	return new ResponseEntity<String>("Imagem não encontrada", HttpStatus.NOT_FOUND);
	}
    
    @Override
    @GetMapping("/post/image/get/{postId}")
	public ResponseEntity<?> getPostImage(@PathVariable Long postId) {
    	Post post = postService.findOne(postId);
    	BufferedImage image;
		try {
			image = getImage(post,null);
		} catch (IOException e) {
			image = null;
		}
    	if(image != null) {
    		return new ResponseEntity<BufferedImage>(image, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<String>("Falha ao carregar imagem", HttpStatus.NOT_FOUND);
    	}
	}
    
    private ResponseEntity<String> returnMessageImageSaved(Boolean successOnSaveImage){
    	if(successOnSaveImage) {
    		return new ResponseEntity<String>("Imagem salva com sucesso", HttpStatus.CREATED);
    	}else {
    		return new ResponseEntity<String>("Formato de imagem inválido ou arquivo muito grande", HttpStatus.NOT_ACCEPTABLE);
    	}
    }
    
    private Boolean saveImage(MultipartFile multiPartFile, Post post, User user)
    {  
    	ImageHelper imageHelper = new ImageHelper(multiPartFile, post, user);
    	if(imageHelper.cleanDiretoryAndSaveImage()) {
    		if(user != null) {
    			user.setHasProfileImage(true);
    			usuarioService.save(user);
    		}else{
    			post.setHasImage(true);
    			postService.save(post);
    		}
    		return true;
    	}
    	imageHelper.tryCleanDirectory();
    	return false;
    }
    
    private BufferedImage getImage(Post post, User user) throws IOException {
    	ImageHelper imageHelper = new ImageHelper(post, user);
    	return imageHelper.getImage();
    }

    
}
