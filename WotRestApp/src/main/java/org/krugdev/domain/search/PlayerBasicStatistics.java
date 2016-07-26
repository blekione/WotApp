package org.krugdev.domain.search;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.WotWebsiteRequest;

import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerBasicStatistics {
	
	@SerializedName("account_id")
	private int accountId;
	private String name;
	private double wins;
	private String platform;
	@SerializedName("battles_count")
	private int battlesCount;
	@SerializedName("profile_url")
	private String profileUrl;
	
	public PlayerBasicStatistics() {
	}

	public PlayerBasicStatistics(int accountId, double wins, String platform, int battlesCount, String profileUrl, String name) {
		this.accountId = accountId;
		this.wins = wins;
		this.platform = platform;
		this.battlesCount = battlesCount;
		this.profileUrl = profileUrl;
		this.name = name;
	}

	public static List<PlayerBasicStatistics> searchPlayers(String query) {
		JavaScriptPage jsonWithPlayers = WotWebsiteRequest.requestPage(query);
		return getPlayersListFromJsonString(jsonWithPlayers.getContent());
	}
	
	private static List<PlayerBasicStatistics> getPlayersListFromJsonString(String jsonAsString) {
		JsonArray players = extractPlayersArrayFromJson(jsonAsString);		
		return getPlayersList(players);
	}

	private static JsonArray extractPlayersArrayFromJson(String jsonAsString) {
		/*
		 * JSON from WoT website comes in format:
		 * {"data":
		 * 		{"items":
		 * 			[ 
		 * 			array of players matched query
		 * 			]}
		 * 		...
		 * }
		 * this method purpose is to extract array from above JSON structure
		 */
		
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(jsonAsString);
		JsonObject rootElement = jsonTree.getAsJsonObject();
		JsonObject data = rootElement.get("data").getAsJsonObject(); 
		return data.get("items").getAsJsonArray();
	}

	private static List<PlayerBasicStatistics> getPlayersList(JsonArray playersJsonArray) {
		PlayerBasicStatistics[] players = 
				new Gson().fromJson(playersJsonArray, PlayerBasicStatistics.class);
 		return Arrays.asList(players);
	}

	@Override
	public String toString() {
		return "PlayerBasic [accountId=" + accountId + ", wins=" + wins + ", platform=" + platform + ", battlesCount="
				+ battlesCount + ", profileUrl=" + profileUrl + ", name=" + name + "]";
	}	
}
