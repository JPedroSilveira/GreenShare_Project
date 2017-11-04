package com.greenshare.exception;

public class SaveOnDatabaseException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public SaveOnDatabaseException(String message) {
		super(message);
	}
}
