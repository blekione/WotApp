package org.krugdev.domain.playerProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.MyJsonParser;
import org.krugdev.domain.Platforms;
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

import com.google.gson.JsonObject;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerProfile {
	private static MyJsonParser parser = new MyJsonParser();

	
	private Map<String, PlayerStatistics> statistics;
	private Platforms platform;
	private String playerId;

	/* tanks */
	private List<String> tanksPlayed;
	private List<TankItems> tankItems;
	
	public PlayerProfile() {
		statistics = new HashMap<>();
	}	
	
	public PlayerProfile(Platforms platform, String playerId) {
		statistics = new HashMap<>();
		this.platform = platform;
		this.playerId = playerId;
	}

	public static PlayerProfile getPlayerProfile(Platforms platform, String id) {
		PlayerProfile playerProfile = new PlayerProfile(platform, id);
		playerProfile.populateWithData();
		return playerProfile;
	}
	
	private void populateWithData() {
		
		WotData data = getPlayerDataFromWotApi();
		
		statistics.put("player", new Player());
		statistics.put("games_counters", new PlayerGamesCounters());
		statistics.put("kills_deaths", new PlayerKillsDeaths());
		statistics.put("damage", new PlayerDamage());
		statistics.put("experience", new PlayerExperience());
		
		statistics.forEach((k, v) -> v.populateWithDataFromJsonDataHolders(data));
	}
	
	private WotData getPlayerDataFromWotApi() {
		WotData data = new WotData();
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

	public Object getObjectData(RequestingServices requestingService, String id, Class<?> class1) {
		JsonObject playerJson = getJsonFromWot(requestingService, id);
		return parser.getClassDataFromJson(playerJson, class1);
	}
	
	private JsonObject getJsonFromWot(RequestingServices requestingService, String id) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, requestingService);		
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		return parser.trimJsonFromRedundantData(playerProfileJsonAsString, id);
	}

	public String getNickname() {
		Player player = (Player) statistics.get("player");
		return player.getNickname();
	}

	public Object getDaysInGame() {
		Player player = (Player) statistics.get("player");
		return player.getDaysInGame();
	} 
	
	public List<String> getTanksPlayed() {
		return tanksPlayed;
	}

	public void setTanksPlayed(List<String> tanksPlayed) {
		this.tanksPlayed = tanksPlayed;
	}

	public List<TankItems> getTankItems() {
		return tankItems;
	}

	public void setTankItems(List<TankItems> tankItems) {
		this.tankItems = tankItems;
	}

	public int getGamesPlayedCounter() {
		PlayerGamesCounters counters = (PlayerGamesCounters) statistics.get("games_counters");
		return counters.getBattlesCount();
	}

	public long getKills() {
		PlayerKillsDeaths killsDeadths = (PlayerKillsDeaths) statistics.get("kills_deaths");
		return killsDeadths.getKills();
	}
	
	public long getDamageDealt() {
		PlayerDamage damage = (PlayerDamage) statistics.get("damage");
		return damage.getDamageDealt();
	}

	public int getHighestExperience() {
		PlayerExperience exp = (PlayerExperience) statistics.get("experience");
		return exp.getHighestExperience();
	}
}
