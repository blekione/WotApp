package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;

@Path("/player")
public interface PlayerProfileResourceRestAnnotations {

	@GET
	@Path("item/{player_id}")
	public StreamingOutput getPlayer(@PathParam("player_id") int playerId);
	
	@GET
	@Path("tanks/{player_id}")
	public StreamingOutput getPlayerTanks(@PathParam("player_id") int playerId);
	
	@GET
	@Path("search/{platform}/{qry}")
	public StreamingOutput search(@PathParam("platform") String platform ,@PathParam("qry") String qry);
}
