package org.krugdev.rservices;


import javax.ws.rs.core.StreamingOutput;

import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.auxiliary.OutputWritter;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.Resource;
import org.krugdev.auxiliary.ResourceFactory;
import org.krugdev.auxiliary.ResourceNotFoundException;

public class WotResource implements WotResourceRestAnnotations {

	Platform platform;
	String resourceParam;

	@Override 
	public StreamingOutput getResource(String platform, String resourceName, String query) {
		setPlatform(platform);
		ResourceFactory resourceFactory = new ResourceFactory();
		Resource resource = resourceFactory.createResource(resourceName);
		try {
		resource.getFromAPI(this.platform, query);
		OutputWritter outputWritter = new OutputWritter();
		return outputStream -> 
			outputWritter.toXml(resource, outputStream);
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("query: " + query 
					+ " returns no data for resource: " + resourceName 
					+ " at platform: " + platform);

		}
	}

	@Override
	public StreamingOutput getPlayerTanks(String platform, String playerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setPlatform(String platform) {
		switch(platform) {
		case "playstation":
			this.platform = Platform.PLAY_STATION;
			break;
		case "xbox":
		default:
			this.platform = Platform.XBOX;
		}		
	}
}
