package org.krugdev.rservice;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.krugdev.rservice.domain.PlayerTankWN8;
import org.krugdev.rservice.domain.PlayerWN8;
import org.krugdev.rservice.domain.SessionWN8;
import org.krugdev.rservice.domain.TankDescriptionXML;

@Path("/playerWN8")
@Produces("application/xml")
@Local
public interface PlayerWN8ResourceRESTAnnotations {

	@GET
	@Path("/{platform}-plt/{playerId}")
	public PlayerWN8 getPlayerWN8(@PathParam("platform") String platform, @PathParam("playerId") int playerId);
	
	@GET
	@Path("/{platform}-plt/{playerId}/tanks")
	public GenericEntity<List<PlayerTankWN8>> getPlayerTanksWN8(@PathParam("platform") String platform, @PathParam("playerId") int playerId);
	
	@PUT
	@Path("/{platform}-plt/{playerId}/{sessionId}-session")
	public Response startNewWN8Session(@PathParam("platform") String platform, @PathParam("playerId") int playerId, @PathParam("sessionId") String sessionId);

	@GET
	@Path("/{platform}-plt/{playerId}/{sessionId}-session")
	public SessionWN8 getRunningWN8Session(@PathParam("platform") String platform, @PathParam("playerId") int playerId, @PathParam("sessionId") String sessionId);
	
	@DELETE
	@Path("/{platform}-plt/{playerId}/{sessionId}-session")
	public SessionWN8 finaliseWN8Session(@PathParam("platform") String platform, @PathParam("playerId") int playerId, @PathParam("sessionId") String sessionId);

	@GET
	@Path("/tank_descriptions")
	public GenericEntity<List<TankDescriptionXML>> getTankDescriptions();
}
