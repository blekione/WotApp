package org.krugdev.domain.player.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerStatisticsJSONBean;

@XmlRootElement
public class PlayerExperience extends PlayerStatistics {
	
	private Long totalExperience;
	private int highestExperience;
	private int highestExpTankId; 

	@Override
	public void populateWithDataFromJsonDataHolder(PlayerJSONBean playerJSONBean) {
		PlayerStatisticsJSONBean statistics = playerJSONBean.getStatistics();
		totalExperience = statistics.getAll().getXp();
		highestExperience = statistics.getMaxXp();
		highestExpTankId = statistics.getMaxXpTankId();
	}

	public Long getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Long totalExperience) {
		this.totalExperience = totalExperience;
	}

	public int getHighestExperience() {
		return highestExperience;
	}

	public void setHighestExperience(int highestExperience) {
		this.highestExperience = highestExperience;
	}

	public int getHighestExpTankId() {
		return highestExpTankId;
	}

	public void setHighestExpTankId(int highestExpTankId) {
		this.highestExpTankId = highestExpTankId;
	}
}
