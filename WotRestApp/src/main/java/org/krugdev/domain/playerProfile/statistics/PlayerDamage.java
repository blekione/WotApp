package org.krugdev.domain.playerProfile.statistics;

import org.krugdev.domain.playerProfile.WotData;
import org.krugdev.domain.playerProfile.JSONDataBeans.StatisticsJSONBean;

public class PlayerDamage implements PlayerStatistics {

	private long damageDealt;
	private long damageAfterTrack;
	private long damageAfterSpot;
	private long damageReceived;
	
	public PlayerDamage() {
	}

	public PlayerDamage(int damageDealt, int damageAfterTrack, int damageAfterSpot, 
			int damageReceived) {
		super();
		this.damageDealt = damageDealt;
		this.damageAfterTrack = damageAfterTrack;
		this.damageAfterSpot = damageAfterSpot;
		this.damageReceived = damageReceived;
	}
	
	@Override
	public void populateWithDataFromJsonDataHolders(WotData data) {
		StatisticsJSONBean statistics = data.getPlayer().getStatistics();
		damageDealt = statistics.getAll().getDamageDealt();
		damageAfterTrack = statistics.getDamageAssistedTrack();
		damageAfterSpot = statistics.getDamageAssistedRadio();
		damageReceived = statistics.getAll().getDamageReceived();
	}
	
	public long getDamageDealt() {
		return damageDealt;
	}

	public long getDamageAfterTrack() {
		return damageAfterTrack;
	}

	public long getDamageAfterSpot() {
		return damageAfterSpot;
	}

	public long getDamageReceived() {
		return damageReceived;
	}
}
