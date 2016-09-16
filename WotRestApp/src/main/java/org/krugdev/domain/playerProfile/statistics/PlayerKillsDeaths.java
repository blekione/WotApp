package org.krugdev.domain.playerProfile.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.playerProfile.WotPlayerData;

@XmlRootElement
public class PlayerKillsDeaths extends PlayerStatistics {
	
	private long kills;
	private long deaths;
	
	public PlayerKillsDeaths() {
	}

	public void populateWithDataFromJsonDataHolders(WotPlayerData data) {
		kills = data.getPlayer().getStatistics().getAll().getFrags();
		deaths = calculateDeaths(data);
	}

	private long calculateDeaths(WotPlayerData data) {
		long gamePlayed = data.getPlayer().getStatistics().getAll().getBattles();
		long gameSurvived = data.getPlayer().getStatistics().getAll().getSurvivedBattles();
		return gamePlayed - gameSurvived;
	}

	public long getKills() {
		return kills;
	}

	public void setKills(long kills) {
		this.kills = kills;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}

}
