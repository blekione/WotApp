package org.krugdev.domain.player.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.player.JSONDataBeans.PlayerGameModeStatisticsJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

@XmlRootElement
public class PlayerKillsDeaths extends PlayerStatistics {
	
	private long kills;
	private long deaths;

	public void populateWithDataFromJsonDataHolder(PlayerJSONBean playerJSONBean) {
		PlayerGameModeStatisticsJSONBean allGamesStats = playerJSONBean.getStatistics().getAll();
		kills = allGamesStats.getFrags();
		deaths = calculateDeaths(allGamesStats);
	}

	private long calculateDeaths(PlayerGameModeStatisticsJSONBean allGamesStats) {
		long gamePlayed = allGamesStats.getBattles();
		long gameSurvived = allGamesStats.getSurvivedBattles();
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
