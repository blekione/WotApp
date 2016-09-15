package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotData;

public abstract class PlayerStatistics {
	
	public abstract void populateWithDataFromJsonDataHolders(WotData data);

}
