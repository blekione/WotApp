package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotPlayerData;

public abstract class PlayerStatistics {
	
	public abstract void populateWithDataFromJsonDataHolders(WotPlayerData data);

}
