package org.krugdev.rservices;

import java.util.List;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.player.Player;
import org.krugdev.domain.player.PlayerProcessor;
import org.krugdev.domain.playerTanks.TankItem;
import org.krugdev.domain.searchPlayers.PlayerBasic;
import org.krugdev.domain.searchPlayers.PlayersBasicSearchProcessor;

public class PlayerResource implements PlayerResourceRestAnnotations {

	Platform platform;
	
	public PlayerResource(Platform platform) {
		this.platform = platform;
	}
	
	@Override 
	public List<PlayerBasic> getPlayers(String query) {
		List<PlayerBasic> players;
		try {
			players = PlayersBasicSearchProcessor.getFromAPI(platform, query);
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("No players marching query: " + query 
					+ " found for platform " + platform);
		}
		return players;
	}

	@Override
	public Player getPlayer(String playerIdString) {
		int playerId;
		Player player;
		try {
			playerId = Integer.parseInt(playerIdString);
			player = PlayerProcessor.getFromAPI(platform, playerId);
			return player;
		} catch (NumberFormatException e) {
			throw new BadRequestException("playerId: " + playerIdString + " need to be a numeric value");
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("No player with playerId: " + playerIdString 
					+ " found for platform " + platform);
		}
	}

	@Override
	public List<TankItem> getPlayerTanks(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
