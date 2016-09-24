package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;

@Path("/wotApi")
public interface WotResourceRestAnnotations {
	
	@GET
	@Path("{platform}/{resource}/{query}")
	public StreamingOutput getResource(@PathParam("platform") String platform, 
			@PathParam("resource") String resourceName, @PathParam("query") String query);

	@GET
	@Path("tanks/{platform}/{player_id}")
	public StreamingOutput getPlayerTanks(@PathParam("platform") String platform, @PathParam("player_id") String playerId);
	
}
