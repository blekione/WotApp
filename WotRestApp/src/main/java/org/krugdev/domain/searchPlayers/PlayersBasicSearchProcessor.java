package org.krugdev.domain.searchPlayers;

import java.util.Arrays;
import java.util.List;

import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlayersBasicSearchProcessor{

	public static List<PlayerBasic> getFromAPI(Platform platform, String query) throws ResourceNotFoundException {
		String jsonPlayersString = getJsonWithPlayersFromWotAPI(platform, query);
		List<PlayerBasic> players = getPlayersFromJSONString(jsonPlayersString);
		if (players.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		setPlayersPlatform(players, platform);
		return players;
	}

	private static String getJsonWithPlayersFromWotAPI(Platform platform, String resourceId) {
		WotWebsiteRequest jsonRequest = new WotWebsiteRequest(platform, RequestingServices.SEARCH);
		String jsonWithPlayers = jsonRequest.getJsonFromWotAPI(resourceId);
		return jsonWithPlayers;
	}
	
	private static List<PlayerBasic> getPlayersFromJSONString(String jsonPlayersString) {
		JsonArray playersJsonArray = trimJsonToGetArray(jsonPlayersString);
		PlayerBasic[] players = JSONParserUtils.getElement(playersJsonArray, new PlayerBasic[1]);
		return Arrays.asList(players);
	}
	
	private static JsonArray trimJsonToGetArray(String jsonPlayersString) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonPlayersString).getAsJsonObject();
		return jsonObject.get("data").getAsJsonArray();		
	}
	
	private static void setPlayersPlatform(List<PlayerBasic> players, Platform platform) {
		for (PlayerBasic player : players) {
			player.setPlatform(platform);
		}	
	}
}
