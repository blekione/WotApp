package org.krugdev.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.google.gson.Gson;
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
		return getPlayersListFromJson(jsonWithPlayers.getContent());
	}
	
	private static List<PlayerBasicStatistics> getPlayersListFromJson(String jsonAsString) {
		JsonObject dataElement = extractDataElementFromJson(jsonAsString);		
		String dataElementAsString = dataElement.toString();
		return getPlayersList(dataElementAsString);
	}

	private static JsonObject extractDataElementFromJson(String jsonInput) {
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(jsonInput);
		JsonObject rootElement = jsonTree.getAsJsonObject();
		return rootElement.get("data").getAsJsonObject();
	}

	private static List<PlayerBasicStatistics> getPlayersList(String jsonInput) {
		Gson gson = new Gson(); 
		return gson.fromJson(jsonInput, PlayerBasicCointainer.class).getItems();
	}

	@Override
	public String toString() {
		return "PlayerBasic [accountId=" + accountId + ", wins=" + wins + ", platform=" + platform + ", battlesCount="
				+ battlesCount + ", profileUrl=" + profileUrl + ", name=" + name + "]";
	}	
}
