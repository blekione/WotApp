package org.krugdev.domain.playerProfile;

import java.util.List;

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

import com.google.gson.JsonObject;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerProfile {

	private Player playerDetails;
	private PlayerGamesCunters gamesCounters;
	private PlayerKillsDeaths killsDeaths;
	private PlayerDamage damage;
	private PlayerExperience experience;
	private int vehiclesSpotted;
	private int baseCapturePoints;
	private int baseDefensePoints;

	/* tanks */
	private List<String> tanksPlayed;
	private List<TankItems> tankItems;
	
	private static Platforms platform;
	private static MyJsonParser parser = new MyJsonParser();

	public PlayerProfile() {
	}	

	public static PlayerProfile getPlayerProfile(Platforms aPlatform, String id) {
		platform = aPlatform; 		
		
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.populateWithData(getPlayerDataFromWotApi(id));
		playerProfile.getPlayerDetails().setPlatform(platform);
		return playerProfile;
	}

	private static WotData getPlayerDataFromWotApi(String id) {
		WotData data = new WotData();
		data.setPlayer((PlayerJSONBean)getObjectData(RequestingServices.PLAYER_PROFILE, id, PlayerJSONBean.class));
		data.setPlayerClan((PlayerClanJSONBean)getObjectData(RequestingServices.PLAYER_CLAN, id, PlayerClanJSONBean.class));
		String clanId = Integer.toString(data.getPlayerClan().getClanId());
		data.setClan((ClanJSONBean)getObjectData(RequestingServices.CLAN, clanId, ClanJSONBean.class));		
		return data;
	}

	public static Object getObjectData(RequestingServices requestingService, String id, Class<?> class1) {
		JsonObject playerJson = getJsonFromWot(requestingService, id);
		return parser.getClassDataFromJson(playerJson, class1);
	}
	
	private static JsonObject getJsonFromWot(RequestingServices requestingService, String id) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, requestingService);		
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		return parser.trimJsonFromRedundantData(playerProfileJsonAsString, id);
	}

	private void populateWithData(WotData data) {
		playerDetails = new Player();
		playerDetails.populateWithDataFromJsonDataHolders(data);
		
		gamesCounters = new PlayerGamesCunters();
		gamesCounters.populateWithDataFromJsonDataHolders(data);
	}

	public Player getPlayerDetails() {
		return playerDetails;
	}

	public void setPlayerDetails(Player playerDetails) {
		this.playerDetails = playerDetails;
	}

	public PlayerGamesCunters getGamesCounters() {
		return gamesCounters;
	}

	public void setGamesCounters(PlayerGamesCunters gamesCounters) {
		this.gamesCounters = gamesCounters;
	}

	public PlayerKillsDeaths getKillsDeaths() {
		return killsDeaths;
	}

	public void setKillsDeaths(PlayerKillsDeaths killsDeaths) {
		this.killsDeaths = killsDeaths;
	}

	public PlayerDamage getDamage() {
		return damage;
	}

	public void setDamage(PlayerDamage damage) {
		this.damage = damage;
	}

	public PlayerExperience getExperience() {
		return experience;
	}

	public void setExperience(PlayerExperience experience) {
		this.experience = experience;
	}

	public int getVehiclesSpotted() {
		return vehiclesSpotted;
	}

	public void setVehiclesSpotted(int vehiclesSpotted) {
		this.vehiclesSpotted = vehiclesSpotted;
	}

	public int getBaseCapturePoints() {
		return baseCapturePoints;
	}

	public void setBaseCapturePoints(int baseCapturePoints) {
		this.baseCapturePoints = baseCapturePoints;
	}

	public int getBaseDefensePoints() {
		return baseDefensePoints;
	}

	public void setBaseDefensePoints(int baseDefensePoints) {
		this.baseDefensePoints = baseDefensePoints;
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
	
}
