package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotData;

public interface PlayerStatistics {
	
	public void populateWithDataFromJsonDataHolders(WotData data);

}
