package org.krugdev.auxiliary;

public interface Resource {
	
	public Resource getFromAPI(Platform platform, String query) throws ResourceNotFoundException;

}
