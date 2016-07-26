package org.krugdev.domain.playerProfile;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerAdvancedStatistics {

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

	public PlayerAdvancedStatistics() {
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
