package org.krugdev.rservices;

import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.Resource;
import org.krugdev.auxiliary.ResourceFactory;
import org.krugdev.auxiliary.ResourceNotFoundException;

public class WotResource implements WotResourceRestAnnotations {

	@Override 
	public Resource getResource(String platformString, String resourceName, String query) {
		Platform platform = Platform.setPlatform(platformString);
		ResourceFactory resourceFactory = new ResourceFactory();
		Resource resource = resourceFactory.createResource(resourceName);
		try {
		resource.getFromAPI(platform, query);
		return resource;
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("query: " + query 
					+ " returns no data for resource: " + resourceName 
					+ " at platform: " + platform);
		}
	}
}
