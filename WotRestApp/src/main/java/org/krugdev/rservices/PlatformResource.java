package org.krugdev.rservices;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.krugdev.auxiliary.Platform;

@Path("/{platform}-plt")
public class PlatformResource {
	
	@PathParam("platform")
	String platformString;
	
	@Path("players")
	public PlayerResource getPlayerResource(){
		Platform platform = Platform.setPlatform(platformString);
		PlayerResource playerResource = new PlayerResource(platform);
		return playerResource;
	}
}
