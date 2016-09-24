package org.krugdev.domain.player.statistics;

import org.krugdev.domain.player.WotPlayerData;

public abstract class PlayerStatistics {
	
	public abstract void populateWithDataFromJsonDataHolder(WotPlayerData data);

}
