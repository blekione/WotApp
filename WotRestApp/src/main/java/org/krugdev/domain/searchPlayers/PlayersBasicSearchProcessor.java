package org.krugdev.domain.searchPlayers;

import java.util.Arrays;
import java.util.List;

import org.krugdev.auxiliary.JSONToObjectBuilder;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;

public class PlayersBasicSearchProcessor{

	public static List<PlayerBasic> getFromAPI(Platform platform, String query) throws ResourceNotFoundException {
		String jsonPlayersString = getJsonWithPlayersFromWotAPI(platform, query);
		JSONToObjectBuilder<PlayerBasic[]> builder = 
				new JSONToObjectBuilder<>(new PlayerBasic[0]);
		PlayerBasic[] playersArray = builder.fromString(jsonPlayersString).build();
		 
		if (playersArray.length == 0) {
			throw new ResourceNotFoundException();
		} else {
			List<PlayerBasic> players = Arrays.asList(playersArray);
			setPlayersPlatform(players, platform);
			return players;
		}
	}

	private static String getJsonWithPlayersFromWotAPI(Platform platform, String resourceId) {
		WotWebsiteRequest jsonRequest = new WotWebsiteRequest(platform, RequestingServices.SEARCH);
		String jsonWithPlayers = jsonRequest.getJsonFromWotAPI(resourceId);
		return jsonWithPlayers;
	}
	
	private static void setPlayersPlatform(List<PlayerBasic> players, Platform platform) {
		for (PlayerBasic player : players) {
			player.setPlatform(platform);
		}	
	}
}
