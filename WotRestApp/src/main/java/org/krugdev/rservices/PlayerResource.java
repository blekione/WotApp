package org.krugdev.rservices;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.GenericEntity;

import org.jboss.resteasy.spi.BadRequestException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.player.Player;
import org.krugdev.domain.player.PlayerProcessor;
import org.krugdev.domain.playerTanks.TankItemsProcessor;
import org.krugdev.domain.playerTanks.TankItem;
import org.krugdev.domain.searchPlayers.PlayerBasic;
import org.krugdev.domain.searchPlayers.PlayersBasicSearchProcessor;

public class PlayerResource implements PlayerResourceRestAnnotations {

	Platform platform;
	
	public PlayerResource(Platform platform) {
		this.platform = platform;
	}
	
	@Override
	public GenericEntity<List<PlayerBasic>> getPlayers(String query) {
		try {
			GenericEntity<List<PlayerBasic>> players = 
					new GenericEntity<List<PlayerBasic>>(
							PlayersBasicSearchProcessor.getFromAPI(platform, query)
							) {};
			return players;
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("No players marching query: " + query 
					+ " found for platform " + platform);
		}
	}

	@Override
	public Player getPlayer(String playerIdString) {
		int playerId = convertPlayerIdToInteger(playerIdString);
		try {
			return PlayerProcessor.getFromAPI(platform, playerId);
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("No player with playerId: " + playerIdString 
					+ " found for platform " + platform);
		}
	}

	@Override
	public GenericEntity<List<TankItem>> getPlayerTanks(String playerIdString) {
		int playerId = convertPlayerIdToInteger(playerIdString);
		try {
			GenericEntity<List<TankItem>> tanks = 
					new GenericEntity<List<TankItem>>(TankItemsProcessor.getFromAPI(platform, playerId)) {};
			return tanks;
		} catch (ResourceNotFoundException e) { 
			throw new NotFoundException("No player with playerId: " + playerIdString 
					+ " found for platform " + platform);
		}
	}
	
	private int convertPlayerIdToInteger(String playerIdString) {
		try {
		return Integer.parseInt(playerIdString);
		} catch (NumberFormatException e) {
			throw new BadRequestException("playerId: " + playerIdString + " need to be a numeric value");
		}
	}
}
