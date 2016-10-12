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
import org.krugdev.domain.player.JSONDataBeans.ClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.player.statistics.PlayerDamage;
import org.krugdev.domain.player.statistics.PlayerExperience;
import org.krugdev.domain.player.statistics.PlayerGamesCounters;
import org.krugdev.domain.player.statistics.PlayerKillsDeaths;
import org.krugdev.domain.player.statistics.PlayerMisc;
import org.krugdev.domain.player.statistics.PlayerStatistics;

import com.google.gson.Gson;
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
	
	public Player() {
	}
	
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
		
		WotPlayerData data = getPlayerDataFromWotApi();
		
		List<PlayerStatistics> statistics= new ArrayList<>();
		
		statistics.add(playerMisc = new PlayerMisc());
		statistics.add( playerGames = new PlayerGamesCounters());
		statistics.add(playerFrags = new PlayerKillsDeaths());
		statistics.add(playerDamage = new PlayerDamage());
		statistics.add(playerExperience = new PlayerExperience());
		
		statistics.forEach((v) -> v.populateWithDataFromJsonDataHolder(data));
		
	}
	
	private WotPlayerData getPlayerDataFromWotApi() throws ResourceNotFoundException {
		WotPlayerData data = new WotPlayerData();
		
		JsonObject playerJson = 
				getJsonFromWot(RequestingServices.PLAYER_PROFILE, playerId).getAsJsonObject();
		data.setPlayer(new Gson().fromJson(playerJson, PlayerJSONBean.class));
		
		JsonObject playerClanJSON = 
				getJsonFromWot(RequestingServices.PLAYER_CLAN, playerId).getAsJsonObject();
		data.setPlayerClan(new Gson().fromJson(playerClanJSON, PlayerClanJSONBean.class));
		
		String clanId = Integer.toString(data.getPlayerClan().getClanId());
		if (!clanId.equals("0")){
			JsonObject clanJSON = getJsonFromWot(RequestingServices.CLAN, clanId).getAsJsonObject();
		data.setClan(new Gson().fromJson(clanJSON, ClanJSONBean.class));
		}
		return data;
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
}
