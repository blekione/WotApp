package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;

@Path("/player")
public interface WotResourceRestAnnotations {

	@GET
	@Path("item/{platform}/{player_id}")
	public StreamingOutput getPlayer(@PathParam("platform") String platform, @PathParam("player_id") String playerId);
	
	@GET
	@Path("tanks/{platform}/{player_id}")
	public StreamingOutput getPlayerTanks(@PathParam("platform") String platform, @PathParam("player_id") String playerId);
	
	@GET
	@Path("search/{platform}/{qry}")
	public StreamingOutput search(@PathParam("platform") String platform ,@PathParam("qry") String qry);
}
