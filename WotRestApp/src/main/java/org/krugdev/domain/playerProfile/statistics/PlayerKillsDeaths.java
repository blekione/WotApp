package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotData;

public class PlayerKillsDeaths implements PlayerStatistics {
	
	private long kills;
	private long deaths;
	
	public PlayerKillsDeaths() {
	}

	public PlayerKillsDeaths(long kills, long deaths) {
		super();
		this.kills = kills;
		this.deaths = deaths;
	}

	public void populateWithDataFromJsonDataHolders(WotData data) {
		kills = data.getPlayer().getStatistics().getAll().getFrags();
		deaths = calculateDeaths(data);
	}

	private long calculateDeaths(WotData data) {
		long gamePlayed = data.getPlayer().getStatistics().getAll().getBattles();
		long gameSurvived = data.getPlayer().getStatistics().getAll().getSurvivedBattles();
		return gamePlayed - gameSurvived;
	}

	public long getKills() {
		return kills;
	}

	public long getDeaths() {
		return deaths;
	}

}
