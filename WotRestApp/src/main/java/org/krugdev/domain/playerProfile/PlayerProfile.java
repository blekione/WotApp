package org.krugdev.domain.playerProfile;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.MyJsonParser;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.PlayerNotFoundException;
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
	
	private Map<Stat, PlayerStatistics> statistics;
	private Platforms platform;
	private String playerId;

	private enum Stat {
		PLAYER,
		GAMES,
		FRAGS,
		DAMAGE,
		EXP;
	}
	
	public PlayerProfile() {
		statistics = new HashMap<>();
	}
	
	public PlayerProfile(Platforms platform, String playerId) {
		statistics = new HashMap<>();
		this.platform = platform;
		this.playerId = playerId.replaceFirst("^0+(?!$)", ""); // trims leading zeros
	}

	public static PlayerProfile getPlayerProfile(Platforms platform, String id) 
			throws PlayerNotFoundException {
		PlayerProfile playerProfile = new PlayerProfile(platform, id);
		playerProfile.populateWithData();
		return playerProfile;
	}
	
	private void populateWithData() throws PlayerNotFoundException {
		
		WotData data = getPlayerDataFromWotApi();
		
		statistics.put(Stat.PLAYER, new Player());
		statistics.put(Stat.GAMES, new PlayerGamesCounters());
		statistics.put(Stat.FRAGS, new PlayerKillsDeaths());
		statistics.put(Stat.DAMAGE, new PlayerDamage());
		statistics.put(Stat.EXP, new PlayerExperience());
		
		statistics.forEach((k, v) -> v.populateWithDataFromJsonDataHolders(data));
	}
	
	private WotData getPlayerDataFromWotApi() throws PlayerNotFoundException {
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

	public Object getObjectData(RequestingServices service, String id, Class<?> class1) 
			throws PlayerNotFoundException {
		JsonObject playerJson = getJsonFromWot(service, id);
		return parser.getClassDataFromJson(playerJson, class1);
	}
	
	private JsonObject getJsonFromWot(RequestingServices service, String id) 
			throws PlayerNotFoundException {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, service);		
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		return parser.trimJsonFromRedundantData(playerProfileJsonAsString, id);
	}

	public String getNickname() {
		Player player = (Player) statistics.get(Stat.PLAYER);
		return player.getNickname();
	}

	public Object getDaysInGame() {
		Player player = (Player) statistics.get(Stat.PLAYER);
		return player.getDaysInGame();
	} 

	public int getGamesPlayedCounter() {
		PlayerGamesCounters counters = (PlayerGamesCounters) statistics.get(Stat.GAMES);
		return counters.getBattlesCount();
	}

	public long getKills() {
		PlayerKillsDeaths killsDeadths = (PlayerKillsDeaths) statistics.get(Stat.FRAGS);
		return killsDeadths.getKills();
	}
	
	public long getDamageDealt() {
		PlayerDamage damage = (PlayerDamage) statistics.get(Stat.DAMAGE);
		return damage.getDamageDealt();
	}

	public int getHighestExperience() {
		PlayerExperience exp = (PlayerExperience) statistics.get(Stat.EXP);
		return exp.getHighestExperience();
	}
}
