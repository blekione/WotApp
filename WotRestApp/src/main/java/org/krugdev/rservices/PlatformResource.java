package org.krugdev.rservices;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.krugdev.auxiliary.Platform;

@Path("/{platform}-plt")
public class PlatformResource {
	
	@PathParam("platform")
	Platform platform;
	
	@Path("player")
	public PlayerResource getPlayerResource(){
		PlayerResource playerResource = new PlayerResource(platform);
		return playerResource;
	}
	
}
