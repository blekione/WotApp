package org.krugdev.domain.playerProfile;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.krugdev.domain.MyJsonParser;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.ResourceNotFoundException;
import org.krugdev.domain.RequestingServices;
import org.krugdev.domain.WotWebsiteRequest;
import org.krugdev.domain.playerProfile.JSONDataBeans.ClanJSONBean;
import org.krugdev.domain.playerProfile.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.playerProfile.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.playerProfile.statistics.Player;
import org.krugdev.domain.playerProfile.statistics.PlayerDamage;
import org.krugdev.domain.playerProfile.statistics.PlayerExperience;
import org.krugdev.domain.playerProfile.statistics.PlayerGamesCounters;
import org.krugdev.domain.playerProfile.statistics.PlayerKillsDeaths;
import org.krugdev.domain.playerProfile.statistics.PlayerStatistics;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@XmlRootElement(name="player_profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerProfile {
	
	@XmlTransient
	private static MyJsonParser parser = new MyJsonParser();
	
	private Platforms platform;
	private String playerId;
	
	private Player player;
	private PlayerDamage playerDamage;
	private PlayerExperience playerExperience;
	private PlayerGamesCounters playerGames;
	private PlayerKillsDeaths playerFrags;
	
	public PlayerProfile() {
	}
	
	public PlayerProfile(Platforms platform, String playerId) {
		this.platform = platform;
		this.playerId = playerId.replaceFirst("^0+(?!$)", ""); // trims leading zeros
	}

	public static PlayerProfile getPlayerProfile(Platforms platform, String playerId) 
			throws ResourceNotFoundException {
		PlayerProfile playerProfile = new PlayerProfile(platform, playerId);
		playerProfile.populateWithData();
		return playerProfile;
	}
	
	private void populateWithData() throws ResourceNotFoundException {
		
		WotPlayerData data = getPlayerDataFromWotApi();
		
		List<PlayerStatistics> statistics= new ArrayList<>();
		
		statistics.add(player = new Player());
		statistics.add( playerGames = new PlayerGamesCounters());
		statistics.add(playerFrags = new PlayerKillsDeaths());
		statistics.add(playerDamage = new PlayerDamage());
		statistics.add(playerExperience = new PlayerExperience());
		
		statistics.forEach((v) -> v.populateWithDataFromJsonDataHolders(data));
	}
	
	private WotPlayerData getPlayerDataFromWotApi() throws ResourceNotFoundException {
		WotPlayerData data = new WotPlayerData();
		
		data.setPlayer(
				(PlayerJSONBean)getObjectData(
						RequestingServices.PLAYER_PROFILE, playerId, PlayerJSONBean.class));
		data.setPlayerClan(
				(PlayerClanJSONBean)getObjectData(
						RequestingServices.PLAYER_CLAN, playerId, PlayerClanJSONBean.class));
		String clanId = Integer.toString(data.getPlayerClan().getClanId());
		data.setClan(
				(ClanJSONBean)getObjectData(
						RequestingServices.CLAN, clanId, ClanJSONBean.class));		
		return data;
	}

	public Object getObjectData(RequestingServices service, String id, Class<?> class1) 
			throws ResourceNotFoundException {
		JsonObject playerJson = getJsonFromWot(service, id).getAsJsonObject();
		return parser.getClassDataFromJson(playerJson, class1);
	}
	
	private JsonElement getJsonFromWot(RequestingServices service, String id) 
			throws ResourceNotFoundException {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, service);		
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		return parser.trimJsonFromRedundantData(playerProfileJsonAsString, id);
	}

	public String getNickname() {
		return player.getNickname();
	}

	public int getDaysInGame() {
		return player.getDaysInGame();
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
