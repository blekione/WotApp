package org.krugdev.rservices;

import java.util.List;

import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.player.Player;
import org.krugdev.domain.player.PlayerProcessor;
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
	public Player getPlayer(String playerId) {
		Player player;
		try {
			player = PlayerProcessor.getFromAPI(platform, playerId);
		} catch (ResourceNotFoundException e) {
			throw new NotFoundException("No player with playerId: " + playerId 
					+ " found for platform " + platform);
		}
		return player;
	}
	
}
