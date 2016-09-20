package org.krugdev.rservices;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.ws.rs.core.StreamingOutput;

import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.ResourceNotFoundException;
import org.krugdev.domain.Resource;
import org.krugdev.domain.ResourceFactory;
import org.krugdev.domain.XMLMarshaller;
import org.krugdev.domain.playerProfile.PlayerProfile;

public class WotResource implements WotResourceRestAnnotations {

	Platforms platform;
	String resourceParam;

	@Override
	public StreamingOutput getResource(String platform, String resourceName, String query) {
		setPlatform(platform);
		ResourceFactory resourceFactory = new ResourceFactory();
		Resource resource = resourceFactory.createResource(resourceName);
		try {
		resource.get(this.platform, query);
		return outputStream -> 
			resource.outputResourceAsXML(outputStream);
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("query: " + query 
					+ " returns no data for resource: " + resourceName 
					+ " at platform: " + platform);

		}
	}

	@Override
	public StreamingOutput getPlayer(String platformString, String playerId) {
		setPlatform(platformString);
		try {
			PlayerProfile playerProfile = PlayerProfile.getPlayerProfile(platform, playerId);
			return outputStream -> 
				outputPlayerProfileAsXML(outputStream, playerProfile);
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("no data for player with id " + playerId + " and platform " + platform);
		}
	}

	private void outputPlayerProfileAsXML(OutputStream outputStream, PlayerProfile playerProfile) {
		PrintStream writer = new PrintStream(outputStream);
		XMLMarshaller.marshallObjectToXML(playerProfile, writer);
		writer.flush(); 	
	}

	@Override
	public StreamingOutput getPlayerTanks(String platform, String playerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setPlatform(String platform) {
		switch(platform) {
		case "playstation":
			this.platform = Platforms.PLAY_STATION;
			break;
		case "xbox":
		default:
			this.platform = Platforms.XBOX;
		}		
	}
}
