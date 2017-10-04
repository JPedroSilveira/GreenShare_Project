package com.seedshare.exception;

/**
 * Exception class related to creating and reading directories
 * @author joao.silva
 */
public class DirectoryException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DirectoryException(String message) {
		super(message);
	}
}
