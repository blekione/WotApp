package org.krugdev.domain;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
