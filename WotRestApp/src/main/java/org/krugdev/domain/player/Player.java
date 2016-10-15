package org.krugdev.domain.player;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.Resource;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;
import org.krugdev.domain.player.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.player.statistics.PlayerDamage;
import org.krugdev.domain.player.statistics.PlayerExperience;
import org.krugdev.domain.player.statistics.PlayerGamesCounters;
import org.krugdev.domain.player.statistics.PlayerKillsDeaths;
import org.krugdev.domain.player.statistics.PlayerMisc;
import org.krugdev.domain.player.statistics.PlayerStatistics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@XmlRootElement(name="player_profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class Player implements Resource {
		
	private Platform platform;
	private String playerId;
	
	private PlayerMisc playerMisc;
	private PlayerDamage playerDamage;
	private PlayerExperience playerExperience;
	private PlayerGamesCounters playerGames;
	private PlayerKillsDeaths playerFrags;
	private PlayerClanJSONBean playerClan;
	
	public Player getFromAPI(Platform platform, String playerId) 
			throws ResourceNotFoundException {
		this.platform =platform;
		this.playerId = trimLeadingZerosFrom(playerId);
		this.populateWithData();
		return this;
	}
	
	private String trimLeadingZerosFrom(String s) {
		return s.replaceFirst("^0+(?!$)", "");
	}

	private void populateWithData() throws ResourceNotFoundException {
		
		PlayerJSONBean playerJSONBean = getPlayerDataFromWotApi();
		
		List<PlayerStatistics> statistics= new ArrayList<>();
		statistics.add(playerMisc = new PlayerMisc());
		statistics.add( playerGames = new PlayerGamesCounters());
		statistics.add(playerFrags = new PlayerKillsDeaths());
		statistics.add(playerDamage = new PlayerDamage());
		statistics.add(playerExperience = new PlayerExperience());
		statistics.forEach((v) -> v.populateWithDataFromJsonDataHolder(playerJSONBean));
		
		playerClan = getPlayerClanDataFromWotAPI();
		
	}
	
	private  PlayerJSONBean getPlayerDataFromWotApi() throws ResourceNotFoundException {		
		JsonObject playerJson = 
				getJsonFromWot(RequestingServices.PLAYER_PROFILE, playerId).getAsJsonObject();
		PlayerJSONBean playerJsonBean = JSONParserUtils.getObject(playerJson, new PlayerJSONBean());
		return playerJsonBean;
	}
	
	private PlayerClanJSONBean getPlayerClanDataFromWotAPI() throws ResourceNotFoundException {
		JsonObject playerClanJSON = 
				getJsonFromWot(RequestingServices.PLAYER_CLAN, playerId).getAsJsonObject();
		PlayerClanJSONBean playerClanJSONBean = 
				JSONParserUtils.getObject(playerClanJSON, new PlayerClanJSONBean());
		return playerClanJSONBean;
	}

	
	private JsonElement getJsonFromWot(RequestingServices service, String id) 
			throws ResourceNotFoundException {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, service);
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		return JSONParserUtils.trimJsonFromRedundantData(playerProfileJsonAsString, id);
	}

	public String getNickname() {
		return playerMisc.getNickname();
	}

	public int getDaysInGame() {
		return playerMisc.getDaysInGame();
	} 

	public int getGamesPlayedCounter() {
		return playerGames.getBattlesCount();
	}

	public Long getKills() {
		return playerFrags.getKills();
	}
	
	public Long getDamageDealt() {
		return playerDamage.getDamageDealt();
	}

	public int getHighestExperience() {
		return playerExperience.getHighestExperience();
	}
	
	public String getPlayerClanId() {
		return playerClan.getClanId();
	}
}
