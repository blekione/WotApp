package org.krugdev.domain;

public class PlayerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException() {
	}
	
	public PlayerNotFoundException(String message) {
		super(message);
	}
}
