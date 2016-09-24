package org.krugdev.domain.searchPlayers;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.Resource;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@XmlRootElement(name="players")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchPlayersResult implements Resource {
	
	@XmlElement(name="player")
	List<PlayerBasic> players;

	@Override
	public Resource getFromAPI(Platform platform, String query) throws ResourceNotFoundException {
			String jsonPlayersString = getJsonWithPlayersFromWotAPI(platform, query);
			players = getPlayersFromJSONString(jsonPlayersString);
			if (players.isEmpty()) {
				throw new ResourceNotFoundException();
			}
			setPlayersPlatform(platform);
			return this;
	}

	private String getJsonWithPlayersFromWotAPI(Platform platform, String resourceId) {
		WotWebsiteRequest jsonRequest = new WotWebsiteRequest(platform, RequestingServices.SEARCH);
		String jsonWithPlayers = jsonRequest.getJsonFromWotAPI(resourceId);
		return jsonWithPlayers;
	}
	
	private List<PlayerBasic> getPlayersFromJSONString(String jsonPlayersString) {
		JsonArray playersJsonArray = trimJsonToGetArray(jsonPlayersString);	
		PlayerBasic[] players = (PlayerBasic[])JSONParserUtils.getList(playersJsonArray, PlayerBasic[].class);
		return Arrays.asList(players);
	}
	
	private JsonArray trimJsonToGetArray(String jsonPlayersString) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonPlayersString).getAsJsonObject();
		return jsonObject.get("data").getAsJsonArray();		
	}

	private void setPlayersPlatform(Platform platform) {
		for (PlayerBasic player : players) {
			player.setPlatform(platform);
		}
	}
	
	public List<PlayerBasic> getPlayers() {
		return players;
	}	
}
