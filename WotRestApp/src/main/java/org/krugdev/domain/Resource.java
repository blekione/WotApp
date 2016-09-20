package org.krugdev.domain;

import java.io.OutputStream;

public interface Resource {
	
	public Resource get(Platforms platform, String resourceId) throws ResourceNotFoundException;

	public void outputResourceAsXML(OutputStream outputStream);

}
