package com.seedshare.controller.photo;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seedshare.controller.Controller;
import com.seedshare.service.user.UserServiceImpl;

/**
 * @author joao.silva
 */
@RestController
public class ImageUploadControllerImpl extends Controller implements ImageUploadController{

	@Autowired
    UserServiceImpl usuarioService;
	
    private final static String IMAGES_PATH = "images/";
    private final static String PROFILE_IMAGE_NAME = "profile.";
    
    @Override
    @PostMapping("/user/upload_photo/profile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("photo") MultipartFile multipartFile) {
    	Boolean imageSaved = saveImage(multipartFile);
    	if(imageSaved == null) {
    		return new ResponseEntity<String>("Formato de imagem inválido", HttpStatus.NOT_ACCEPTABLE);
    	}
    	if(imageSaved) {
        	return new ResponseEntity<String>("Imagem de perfil salva com sucesso", HttpStatus.CREATED);
    	}else {
    	    return new ResponseEntity<String>("Imagem grande demais", HttpStatus.NOT_ACCEPTABLE);
    	}
    }
    
    private Boolean saveImage(MultipartFile multiPartFile)
    {  
    	if(multiPartFile != null) {
        	String fileFormat = multiPartFile.getContentType().substring(multiPartFile.getContentType().indexOf('/')+1);
        	if(fileFormat.equals("png")||fileFormat.equals("jpg")) {
        		char[] numbers = getCurrentUser().getId().toString().toCharArray();
        		StringBuilder  directory = new StringBuilder();
        		for(char number : numbers){
        			directory.append(number).append("/");
        		}
        		java.io.File file = new java.io.File(IMAGES_PATH+directory.toString());
        		file.mkdirs();
                try {
                	if(multiPartFile.getSize()<500000) {
                		file = new java.io.File(file.getPath()+"/"+PROFILE_IMAGE_NAME+fileFormat);
                		FileOutputStream fos = new FileOutputStream(file);      
    		            fos.write(multiPartFile.getBytes());
    		            fos.close();	
    		        	return file.exists(); 
                	}else {
                		return false;
                	}
                } catch (IOException ex) {
                	return null;
                }
        	}
    	}
        return null;
    }
}
