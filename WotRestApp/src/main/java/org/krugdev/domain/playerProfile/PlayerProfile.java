package org.krugdev.domain.playerProfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.Platforms;
import org.krugdev.domain.RequestingServices;
import org.krugdev.domain.WotWebsiteRequest;
import org.krugdev.domain.playerProfile.dataFromJSON.PlayerProfileData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerProfile {

	private PlayerDetails playerDetails;
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
	
	static JsonParser parser = new JsonParser();

	public PlayerProfile() {
	}	

	public static PlayerProfile getPlayerProfile(Platforms platform, String id) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, RequestingServices.PLAYER_PROFILE);
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		JsonObject playerProfileIDDataJson = getProfileDataFromJson(playerProfileJsonAsString, id);
		PlayerProfileData playerData = getDataFromJson(playerProfileIDDataJson);
		PlayerProfile playerProfile = populateWithData(playerData, platform);
		return playerProfile;
	}
	
	private static PlayerProfile populateWithData(PlayerProfileData playerData, Platforms platform) {
		PlayerProfile playerProfile = new PlayerProfile();
		PlayerDetails playerDetails = PlayerDetails.populateWithData(playerData, platform);
		playerProfile.setPlayerDetails(playerDetails);
		return playerProfile;
	}

	private static PlayerProfileData getDataFromJson(JsonObject playerProfileIDDataJson) {
		Gson gson = new Gson();
		return gson.fromJson(playerProfileIDDataJson, PlayerProfileData.class);
	}

	private static JsonObject getProfileDataFromJson(String playerProfileJsonAsString, String id) {
		JsonObject playerProfileJson = parser.parse(playerProfileJsonAsString).getAsJsonObject();
		JsonObject playerProfileDataJson = playerProfileJson.get("data").getAsJsonObject();
		return playerProfileDataJson.get(id).getAsJsonObject();
	}

	public PlayerDetails getPlayerDetails() {
		return playerDetails;
	}

	public void setPlayerDetails(PlayerDetails playerDetails) {
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
