package org.krugdev.domain.playerTanks;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.playerTanks.JSONDataBeans.TankGameModeStatistics;
import org.krugdev.domain.playerTanks.JSONDataBeans.TankJSONBean;

@XmlRootElement
public class TankItem {

	@XmlElement
	private int tankId;
	@XmlElement
	private String tankName;
	@XmlElement
	private int gamesCount;
	@XmlElement
	private int frags;
	@XmlElement
	private long damageDealt;
	@XmlElement
	private int spottedTanks;
	@XmlElement
	private int kills;
	@XmlElement
	private int defencePoints;
	@XmlElement
	private double winRatio;
	
	public TankItem populateWithDataFromJSONDataHolder(TankJSONBean dataHolder) {
		TankGameModeStatistics gameModeStats = dataHolder.getAll();
		this.tankId = dataHolder.getTankId();
		this.gamesCount = gameModeStats.getBattles();
		this.frags = gameModeStats.getFrags();
		this.damageDealt = gameModeStats.getDamageDealt();
		this.spottedTanks = gameModeStats.getSpotted();
		this.kills = gameModeStats.getFrags();
		this.defencePoints = gameModeStats.getDroppedCapturePoints();
		this.winRatio = (gameModeStats.getWins() / (double)gamesCount * 100.0);
		return this;
	}

	public int getTankId() {
		return tankId;
	}

	public String getTankName() {
		return tankName;
	}

	public int getGamesCount() {
		return gamesCount;
	}

	public int getFrags() {
		return frags;
	}

	public long getDamageDealt() {
		return damageDealt;
	}

	public int getSpottedTanks() {
		return spottedTanks;
	}

	public int getKills() {
		return kills;
	}

	public int getDefencePoints() {
		return defencePoints;
	}

	public double getWinRatio() {
		return winRatio;
	}	
}
