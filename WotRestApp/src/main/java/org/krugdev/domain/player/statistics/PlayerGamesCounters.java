package org.krugdev.domain.player.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.player.JSONDataBeans.PlayerGameModeStatisticsJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

@XmlRootElement
public class PlayerGamesCounters extends PlayerStatistics {

	private int battlesCount;
	private int battlesWins;
	private int battlesLosses;
	private int battlesDraws;
	private int battlesSurvived;
	
	public void populateWithDataFromJsonDataHolder(PlayerJSONBean playerJSONBean) {
		PlayerGameModeStatisticsJSONBean allGamesStats = playerJSONBean.getStatistics().getAll();
		battlesCount = allGamesStats.getBattles();
		battlesWins = allGamesStats.getWins();
		battlesLosses = allGamesStats.getLosses();
		battlesSurvived = allGamesStats.getSurvivedBattles();
		battlesDraws = battlesCount - (battlesWins + battlesLosses);
	}

	public int getBattlesCount() {
		return battlesCount;
	}

	public void setBattlesCount(int battlesCount) {
		this.battlesCount = battlesCount;
	}

	public int getBattlesWins() {
		return battlesWins;
	}

	public void setBattlesWins(int battlesWins) {
		this.battlesWins = battlesWins;
	}

	public int getBattlesLosses() {
		return battlesLosses;
	}

	public void setBattlesLosses(int battlesLosses) {
		this.battlesLosses = battlesLosses;
	}

	public int getBattlesDraws() {
		return battlesDraws;
	}

	public void setBattlesDraws(int battlesDraws) {
		this.battlesDraws = battlesDraws;
	}

	public int getBattlesSurvived() {
		return battlesSurvived;
	}

	public void setBattlesSurvived(int battlesSurvived) {
		this.battlesSurvived = battlesSurvived;
	}
}
