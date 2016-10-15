package org.krugdev.domain.player.statistics;

import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

public abstract class PlayerStatistics {
	
	public abstract void populateWithDataFromJsonDataHolder(PlayerJSONBean playerJSONBean);

}
