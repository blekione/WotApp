package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotData;
import org.krugdev.domain.playerProfile.JSONDataBeans.StatisticsJSONBean;

public class PlayerExperience implements PlayerStatistics {
	
	private long totalExperience;
	private int highestExperience;
	private int highestExpTankId; 

	public PlayerExperience() {
	}

	public PlayerExperience(int totalExp, int highestExp) {
		super();
		this.totalExperience = totalExp;
		this.highestExperience = highestExp;
	}

	@Override
	public void populateWithDataFromJsonDataHolders(WotData data) {
		StatisticsJSONBean statistics = data.getPlayer().getStatistics();
		totalExperience = statistics.getAll().getXp();
		highestExperience = statistics.getMaxXp();
		highestExpTankId = statistics.getMaxXpTankId();
	}

	public long getTotalExperience() {
		return totalExperience;
	}

	public int getHighestExperience() {
		return highestExperience;
	}

	public int getHighestExpTankId() {
		return highestExpTankId;
	}
}
