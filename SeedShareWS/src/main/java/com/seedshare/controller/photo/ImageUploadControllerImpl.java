package com.seedshare.controller.user.photo;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seedshare.controller.Controller;
import com.seedshare.entity.User;
import com.seedshare.service.user.UserServiceImpl;
import com.seedshare.uploadCare.api.Client;
import com.seedshare.uploadCare.api.File;
import com.seedshare.uploadCare.upload.FileUploader;
import com.seedshare.uploadCare.upload.UploadFailureException;
import com.seedshare.uploadCare.upload.Uploader;

/**
 * @author joao.silva
 */
@RestController
@RequestMapping("/user/image/")
public class ImageUploadControllerImpl extends Controller implements ImageUploadController{

	@Autowired
    UserServiceImpl usuarioService;
	
    @Override
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("photo") MultipartFile multipartFile) {
    	java.io.File file = convert(multipartFile);
    	if(file!=null) {
    		if(UploadPhoto(file)) {
        	    return new ResponseEntity<String>("Imagem salva com sucesso", HttpStatus.CREATED);
    		}else {
        	    return new ResponseEntity<String>("Erro ao salvar imagem", HttpStatus.CONFLICT);
    		}
    	}else {
    	    return new ResponseEntity<String>("Formato de imagem inválido", HttpStatus.NOT_ACCEPTABLE);
    	}
    }
    
    private java.io.File convert(MultipartFile file)
    {    
        java.io.File convFile = new java.io.File(file.getOriginalFilename());
        try {
        convFile.createNewFile(); 
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(file.getBytes());
        fos.close(); 
        return convFile;
        }catch(IOException ex) {
        	return null;
        }
    }
    
    private boolean UploadPhoto(java.io.File file) {
    	Client client = Client.getClient();
    	Uploader uploader = new FileUploader(client, file);
    	try {
    	    File returnFile = uploader.upload().save();
    	    SavePhotoReference(returnFile.getFileId());
    	    return true;
    	} catch (UploadFailureException e) {
    		return false;
    	}
    }
    
    private void SavePhotoReference(String photoId) {
    	User user = getCurrentUser();
    	user.setPhotoId(photoId);
    }
}
