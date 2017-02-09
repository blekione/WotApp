package org.krugdev.rservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.krugdev.domain.player.Player;
import org.krugdev.domain.playerTanks.TankItem;
import org.krugdev.domain.searchPlayers.PlayerBasic;

@Produces("application/xml")
public interface PlayerResourceRestAnnotations {
	
	@GET
	@Path("search/{query}")
	@Wrapped(element="players")
	public GenericEntity<List<PlayerBasic>> getPlayers(@PathParam("query") String query);
	
	@GET
	@Path("{playerId}")
	public Player getPlayer(@PathParam("playerId") String playerIdString);
	
	@GET
	@Path("{playerId}/tanks")
	@Wrapped(element="tanks")
	public GenericEntity<List<TankItem>> getPlayerTanks(@PathParam("playerId") String playerIdString);


}
