package com.greenshare.helpers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class Base64MultpartFile implements MultipartFile {
	private final byte[] imgContent;

	public Base64MultpartFile(byte[] imgContent) {
		this.imgContent = imgContent;
	}

	@Override
	public String getName() {
		return "PostImage";
	}

	@Override
	public String getOriginalFilename() {
		return "PostImage";
	}

	@Override
	public String getContentType() {
		return "png";
	}

	@Override
	public boolean isEmpty() {
		return imgContent == null || imgContent.length == 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(imgContent);
	}

	@SuppressWarnings("resource")
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(imgContent);
	}
}
