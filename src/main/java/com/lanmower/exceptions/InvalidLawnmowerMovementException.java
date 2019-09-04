package com.lanmower.exceptions;

public class InvalidLawnmowerMovementException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidLawnmowerMovementException(String message) {
		super(message);
	}
}
