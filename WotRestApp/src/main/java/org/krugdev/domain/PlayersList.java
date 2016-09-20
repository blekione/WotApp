package org.krugdev.domain;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.krugdev.domain.search.PlayerBasic;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlayersList implements Resource {
	
	List<PlayerBasic> playersList;
	
	public PlayersList() {
	}

	@Override
	public Resource get(Platforms platform, String resourceId) throws ResourceNotFoundException {
			String jsonPlayersString = getJsonWithPlayersFromWotAPI(platform, resourceId);
			playersList = getPlayersListFromJsonString(jsonPlayersString);
			if (playersList.isEmpty()) {
				throw new ResourceNotFoundException();
			}
			setPlayersPlatform(platform);
			return this;
	}

	private List<PlayerBasic> getPlayersListFromJsonString(String jsonPlayersString) {
		JsonArray players = trimJsonToGetArray(jsonPlayersString);		
		return getPlayersList(players);
	}
	
	private JsonArray trimJsonToGetArray(String jsonPlayersString) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonPlayersString).getAsJsonObject();
		return jsonObject.get("data").getAsJsonArray();
	
	}

	private String getJsonWithPlayersFromWotAPI(Platforms platform, String resourceId) {
		WotWebsiteRequest jsonRequest = new WotWebsiteRequest(platform, RequestingServices.SEARCH);
		String jsonWithPlayers = jsonRequest.getJsonFromWotAPI(resourceId);
		return jsonWithPlayers;
		}

	private List<PlayerBasic> getPlayersList(JsonArray playersJsonArray) {
		PlayerBasic[] players = 
				new Gson().fromJson(playersJsonArray, PlayerBasic[].class);
 		return Arrays.asList(players);
	}

	private void setPlayersPlatform(Platforms platform) {
		for (PlayerBasic player : playersList) {
			player.setPlatform(platform);
		}
	}
	
	@Override
	public void outputResourceAsXML(OutputStream outputStream) {
		PrintStream writer = new PrintStream(outputStream);
		XMLMarshaller.marshallListToXML(playersList, "players", writer);
		writer.flush();
	}
	
	
}
