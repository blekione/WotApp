package org.krugdev.rservice;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.krugdev.rservice.domain.PlayerWN8;

@Path("test/playerWN8")
@Produces("application/xml")
@Local
public interface PlayerWN8ResourceRESTAnnotations {

	@GET
	@Path("{playerId}")
	public PlayerWN8 getPlayerWN8(@PathParam("playerId") String playerIdString);
}
