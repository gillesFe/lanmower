package com.lanmower.exceptions;

public class InvalidLawnSpaceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidLawnSpaceException(String message) {
		super(message);
	}
}
