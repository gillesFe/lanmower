package com.lanmower.exceptions;

public class InvalidLawnmowerPositionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidLawnmowerPositionException(String message) {
		super(message);
	}
}
