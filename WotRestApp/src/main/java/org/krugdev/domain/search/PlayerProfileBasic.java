package org.krugdev.domain.search;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.Platforms;
import org.krugdev.domain.RequestingServices;
import org.krugdev.domain.WotWebsiteRequest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerProfileBasic {
	
	@SerializedName("account_id")
	private int accountId;
	@SerializedName("nickname")
	private String name;
	private String platform;
	
	public PlayerProfileBasic() {
	}

	public PlayerProfileBasic(int accountId, String name) {
		this.accountId = accountId;
		this.name = name;
	}

	public static List<PlayerProfileBasic> getPlayers(Platforms platform, String query) {
		try {
			String jsonWithPlayers = getJsonWithPlayersFromWotAPI(platform, query);
			List<PlayerProfileBasic> players = getPlayersListFromJsonString(jsonWithPlayers);
			players = setPlayersPlatform(players, platform);
			return players;
		} catch (NullPointerException e) {
			return Collections.emptyList();
		}
	}
	
	public static String getJsonWithPlayersFromWotAPI(Platforms platform, String query) {
		WotWebsiteRequest jsonRequest = new WotWebsiteRequest(platform, RequestingServices.SEARCH);
		String jsonWithPlayers = jsonRequest.getJsonFromWotAPI(query);
		return jsonWithPlayers;
		
	}
	
	private static List<PlayerProfileBasic> getPlayersListFromJsonString(String jsonAsString) {
		JsonArray players = extractPlayersArrayFromJson(jsonAsString);		
		return getPlayersList(players);
	}

	private static JsonArray extractPlayersArrayFromJson(String jsonAsString) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonAsString).getAsJsonObject();
		return jsonObject.get("data").getAsJsonArray();
	}

	private static List<PlayerProfileBasic> getPlayersList(JsonArray playersJsonArray) {
		PlayerProfileBasic[] players = 
				new Gson().fromJson(playersJsonArray, PlayerProfileBasic[].class);
 		return Arrays.asList(players);
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getPlatform() {
		return this.platform;
	}
	
	private static List<PlayerProfileBasic> setPlayersPlatform(List<PlayerProfileBasic> players, Platforms platform) {
		for(PlayerProfileBasic player : players) {
			player.setPlatform(platform.toString());
		}
		return players;
	}

}
