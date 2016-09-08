package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotData;

public class PlayerGamesCounters implements PlayerStatistics {

	private int battlesCount;
	private int battlesWins;
	private int battlesLosses;
	private int battlesDraws;
	private int battlesSurvived;
	
	public PlayerGamesCounters() {
	}

	public void populateWithDataFromJsonDataHolders(WotData data) {
		battlesCount = data.getPlayer().getStatistics().getAll().getBattles();
		battlesWins = data.getPlayer().getStatistics().getAll().getWins();
		battlesLosses = data.getPlayer().getStatistics().getAll().getLosses();
		battlesSurvived = data.getPlayer().getStatistics().getAll().getSurvivedBattles();
	}

	public int getBattlesCount() {
		return battlesCount;
	}

	public int getBattlesWins() {
		return battlesWins;
	}

	public int getBattlesLosses() {
		return battlesLosses;
	}

	public int getBattlesDraws() {
		return battlesDraws;
	}

	public int getBattlesSurvived() {
		return battlesSurvived;
	}
}
