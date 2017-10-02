package com.seedshare.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.seedshare.entity.Post;
import com.seedshare.entity.User;

/**
 * @author joao.silva
 */
public class ImageHelper {

	private final static String IMAGES_DIRECTORY = "images/";
	private final static String PROFILE_DIRECTORY = "profile/";
	private final static String POST_DIRECTORY = "post/";
	private final static String PROFILE_IMAGE_NAME = "profile.";
	private final static String POST_IMAGE_NAME = "post.";
	private final static Integer MAX_FILE_SIZE = 5000000;

	private MultipartFile multiPartFile;

	private Post post;

	private Boolean isPostImage;

	private Boolean isProfilePhoto;

	private File imageDirectory;

	private File image;

	private String imageFormat;

	private User user;

	public ImageHelper(MultipartFile multiPartFile, Post post, User user) {
		this.multiPartFile = multiPartFile;
		this.post = post;
		this.isPostImage = (post != null);
		this.user = user;
		this.isProfilePhoto = (user != null);
		configureImageFormat();
		genereteImageDirectory();
	}
	
	public ImageHelper(Post post, User user) {
		this.multiPartFile = null;
		this.post = post;
		this.user = user;
		this.isPostImage = (post != null);
		this.isProfilePhoto = (user != null);
		genereteImageDirectory();
		configureImageFormat();
	}

	public Boolean cleanDiretoryAndSaveImage() {
		if (this.multiPartFile != null && isValidImage() && tryCleanDirectory()) {
			this.image = getImageFile();
			try {
				saveImage();
				return true;
			} catch (IOException ex) {
				return null;
			}
		}
		return false;
	}
	
	public BufferedImage getImage() {
		File imageFile = getImageFile();
		if(imageFile != null && imageFile.isFile()) {
				try {
					return ImageIO.read(imageFile);
				} catch (IOException e) {
					return null;
				}
		}
		return null;
	}

	public Boolean tryCleanDirectory() {
		if (this.imageDirectory.isDirectory()) {
			File[] files = this.imageDirectory.listFiles();
			for (File file : files) {
				Boolean fileIsDeleted = file.delete();
				if(!fileIsDeleted) return false;
			}
			return true;
		}
		return false;
	}

	private Boolean isValidImage() {
		return this.multiPartFile.getSize() <= MAX_FILE_SIZE
				&& (this.imageFormat.equals("png") || this.imageFormat.equals("jpg") || this.imageFormat.equals("jpeg"));
	}

	private void genereteImageDirectory() {
		if (!(this.isPostImage && this.isProfilePhoto)) {
			this.isProfilePhoto = true;
			if (this.isProfilePhoto) {
				this.imageDirectory = genereteProfilePhotoDirectory();
			} else if (this.isPostImage) {
				this.imageDirectory = generetePostImageDirectory();
			}
		}
	}

	private File getImageFile() {
		if(this.isPostImage) {
			return new File(imageDirectory.getPath() + "/" + POST_IMAGE_NAME + this.imageFormat);
		}else if(this.isProfilePhoto) {
			return new File(imageDirectory.getPath() + "/" + PROFILE_IMAGE_NAME + this.imageFormat);
		}
		return null;
	}

	private void configureImageFormat() {
		if(this.multiPartFile != null) {
			this.imageFormat = this.multiPartFile.getContentType().substring(this.multiPartFile.getContentType().indexOf('/') + 1);
		}else {
			File[] files = this.imageDirectory.listFiles();
			this.imageFormat = files[0].getName().substring(files[0].getName().indexOf(".")+1);
		}
	}

	private void saveImage() throws IOException {
		FileOutputStream imageOutput = new FileOutputStream(this.image);
		imageOutput.write(multiPartFile.getBytes());
		imageOutput.close();
	}

	private File genereteProfilePhotoDirectory() {
		char[] idInCharArray = this.user.getId().toString().toCharArray();
		String idDirectory = getIdDirectory(idInCharArray);
		return createDirectory(new java.io.File(IMAGES_DIRECTORY + PROFILE_DIRECTORY + idDirectory));
	}

	private File generetePostImageDirectory() {
		char[] idInCharArray = this.post.getId().toString().toCharArray();
		String idDirectory = getIdDirectory(idInCharArray);
		return createDirectory(new java.io.File(IMAGES_DIRECTORY + POST_DIRECTORY + idDirectory));
	}

	private File createDirectory(File directory) {
		if (directory != null) {
			if (directory.exists()) {
				return directory;
			} else {
				if (directory.mkdirs()) {
					return directory;
				}
			}
		}
		return null;
	}
	private String getIdDirectory(char[] idInCharArray) {
		StringBuilder idDirectory = new StringBuilder();

		for (char number : idInCharArray) {
			idDirectory.append(number).append("/");
		}

		return idDirectory.toString();
	}
}
