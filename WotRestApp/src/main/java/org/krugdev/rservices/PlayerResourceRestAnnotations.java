package org.krugdev.rservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.krugdev.domain.player.Player;
import org.krugdev.domain.searchPlayers.PlayerBasic;

@Path("/resource")
@Produces("application/xml")
public interface PlayerResourceRestAnnotations {
	
	@GET
	@Path("search/{query}")
	public List<PlayerBasic> getPlayers(@PathParam("query") String query);@Produces("application/xml")
	
	@GET
	@Path("{playerId}")
	public Player getPlayer(@PathParam("playerId") String playerId);

}
