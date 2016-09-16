package org.krugdev.domain.playerProfile.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.playerProfile.WotPlayerData;

@XmlRootElement
public class PlayerGamesCounters extends PlayerStatistics {

	private int battlesCount;
	private int battlesWins;
	private int battlesLosses;
	private int battlesDraws;
	private int battlesSurvived;
	
	public PlayerGamesCounters() {
	}

	public void populateWithDataFromJsonDataHolders(WotPlayerData data) {
		battlesCount = data.getPlayer().getStatistics().getAll().getBattles();
		battlesWins = data.getPlayer().getStatistics().getAll().getWins();
		battlesLosses = data.getPlayer().getStatistics().getAll().getLosses();
		battlesSurvived = data.getPlayer().getStatistics().getAll().getSurvivedBattles();
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
