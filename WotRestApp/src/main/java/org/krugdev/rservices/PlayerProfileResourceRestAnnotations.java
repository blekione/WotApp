package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;

@Path("/player")
public interface PlayerProfileResourceRestAnnotations {

	@GET
	@Path("{player_id}")
	public StreamingOutput getPlayer(@PathParam("player_id") int playerId);
	
	@GET
	@Path("{player_id}/tanks")
	public StreamingOutput getPlayerTanks(@PathParam("player_id") int playerId);
}
