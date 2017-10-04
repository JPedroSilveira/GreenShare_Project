package com.seedshare.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.seedshare.entity.interfaces.PhotogenicEntity;
import com.seedshare.enumeration.PhotoType;
import com.seedshare.exception.DirectoryException;

/**
 * @author joao.silva
 */
public class ImageHelper {
	
	/*	  To use ImageHelper	
	 *
	 * 	- Create a new value in Enum PhotoType Where the 'directoryName' will be used to name the folder where the images are saved.
	 *  - Set a static final attribute called PHOTO_TYPE that references the Enum of your Entity Class on the Enum PhotoType.
	 *  - Implements PhotogenicEntity interface on your Entity Class and set the getHasImage(), setHasImage() 
	 *    and getPhotoType() with the correct variables.
	 *  - Create the routes on ImageUploadController and implement they on ImageUploadControllerImpl, on route method you can call
	 * 	  the methods saveImage and getImage.
	 *  - On PhotogenicServiceImpl you will need to add a conditional to save the hasImage attribute using your entity's repository.
	 *  - The image will be saved based on the entity id and only one image per entity can be saved.
	 *  - On save a new image the old is deleted.
	 *  
	 * */
	private final static String IMAGES_DIRECTORY = "images/";

	private final static int MAX_FILE_SIZE = 5000000;

	private MultipartFile multiPartFile;
	
	private PhotogenicEntity entity;
	
	private PhotoType photoType;

	private File imageDirectory;

	private File image;

	private String imageFormat;

	private Long id;

	public ImageHelper(PhotogenicEntity entity) throws DirectoryException {
		this.entity = entity;
		this.id = this.entity.getId();
		this.photoType = this.entity.getPhotoType();
		this.imageDirectory = getOrTryCreateImageDirectory();
	}

	public Boolean save(MultipartFile multiPartFile) throws IOException{
		this.multiPartFile = multiPartFile;
		this.imageFormat = this.multiPartFile.getContentType().substring(this.multiPartFile.getContentType().indexOf('/') + 1);
		if (isValidImage() && cleanDirectory()) {
			this.image = getImageFile();
			FileOutputStream imageOutput = new FileOutputStream(this.image);
			imageOutput.write(multiPartFile.getBytes());
			imageOutput.close();
			return true;
		}
		return false;
	}

	public BufferedImage getImage() throws IOException{
		File[] files = this.imageDirectory.listFiles();
		this.imageFormat = files[0].getName().substring(files[0].getName().indexOf(".")+1);
		File imageFile = getImageFile();			
		if(imageFile != null && imageFile.isFile()) {
			return ImageIO.read(imageFile);
		}
		return null;
	}
	
	private Boolean isValidImage() {
		return this.multiPartFile != null 
				&& this.multiPartFile.getSize() <= MAX_FILE_SIZE
				&& (this.imageFormat.equals("png") || this.imageFormat.equals("jpg") || this.imageFormat.equals("jpeg"));
	}
	
	private File getImageFile() {
		return new File(imageDirectory.getPath().concat("/").concat(this.photoType.getDirectoryName()).concat(this.imageFormat));
	}
	
	private File getOrTryCreateImageDirectory() throws DirectoryException {
		char[] idInCharArray = this.id.toString().toCharArray();
		String idDirectory = getIdDirectory(idInCharArray);
		File file = new File(IMAGES_DIRECTORY.concat("/").concat(this.photoType.getDirectoryName()).concat("/").concat(idDirectory));
		if(!file.exists() || !file.mkdirs()) {
			throw new DirectoryException("Falha ao encontrar diretório da imagem.");
		}
		return file;
	}
	
	private String getIdDirectory(char[] idInCharArray) {
		StringBuilder idDirectory = new StringBuilder();

		for (char number : idInCharArray) {
			idDirectory.append(number).append("/");
		}

		return idDirectory.toString();
	}
	
	private Boolean cleanDirectory() {
		if (this.imageDirectory.isDirectory()) {
			File[] files = this.imageDirectory.listFiles();
			for (File file : files) {
				Boolean fileIsDeleted = file.delete();
				if(!fileIsDeleted) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
