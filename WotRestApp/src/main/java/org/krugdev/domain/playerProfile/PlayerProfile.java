package org.krugdev.domain.playerProfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.Platforms;
import org.krugdev.domain.RequestingServices;
import org.krugdev.domain.WotWebsiteRequest;
import org.krugdev.domain.playerProfile.JsonDataBeans.Clan;
import org.krugdev.domain.playerProfile.JsonDataBeans.PlayerClan;
import org.krugdev.domain.playerProfile.JsonDataBeans.Player;

import com.google.gson.JsonObject;

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

	public PlayerProfile() {
	}	

	public static PlayerProfile getPlayerProfile(Platforms platform, String id) {
		MyJsonParser parser = new MyJsonParser();
		WotWebsiteRequest request = new WotWebsiteRequest(platform, RequestingServices.PLAYER_PROFILE);
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		JsonObject playerProfileIDDataJson = parser.trimJsonFromRedundantData(playerProfileJsonAsString, id);
		
		request.setRequestingService(RequestingServices.PLAYER_CLAN);
		String playerClanJsonAsString = request.getJsonFromWotAPI(id);
		JsonObject playerClanIDDataJson = parser.trimJsonFromRedundantData(playerClanJsonAsString, id);
		
		
		Player playerData = (Player)parser.getClassDataFromJson(playerProfileIDDataJson, Player.class);
		PlayerClan playerClan = (PlayerClan)parser.getClassDataFromJson(playerClanIDDataJson, PlayerClan.class);
		
		request.setRequestingService(RequestingServices.CLAN);
		String clanId = Integer.toString(playerClan.getClanId());
		String clanJsonAsString = request.getJsonFromWotAPI(clanId);
		JsonObject clanProfileIDDataJson = parser.trimJsonFromRedundantData(clanJsonAsString, clanId);
		Clan clan = (Clan)parser.getClassDataFromJson(clanProfileIDDataJson, Clan.class);
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.populateWithData(playerData, playerClan, clan);
		playerProfile.getPlayerDetails().setPlatform(platform);
		return playerProfile;
	}
	
	private void populateWithData(Player playerData, PlayerClan playerClan, Clan clan) {
		
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
