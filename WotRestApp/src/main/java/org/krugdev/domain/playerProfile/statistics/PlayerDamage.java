package org.krugdev.domain.playerProfile.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.playerProfile.WotPlayerData;
import org.krugdev.domain.playerProfile.JSONDataBeans.StatisticsJSONBean;

@XmlRootElement
public class PlayerDamage extends PlayerStatistics {

	private Long damageDealt;
	private Long damageAfterTrack;
	private Long damageAfterSpot;
	private Long damageReceived;
	
	public PlayerDamage() {
	}

	public PlayerDamage(Long damageDealt, Long damageAfterTrack, Long damageAfterSpot, 
			Long damageReceived) {
		super();
		this.damageDealt = damageDealt;
		this.damageAfterTrack = damageAfterTrack;
		this.damageAfterSpot = damageAfterSpot;
		this.damageReceived = damageReceived;
	}
	
	@Override
	public void populateWithDataFromJsonDataHolders(WotPlayerData data) {
		StatisticsJSONBean statistics = data.getPlayer().getStatistics();
		damageDealt = statistics.getAll().getDamageDealt();
		damageAfterTrack = statistics.getDamageAssistedTrack();
		damageAfterSpot = statistics.getDamageAssistedRadio();
		damageReceived = statistics.getAll().getDamageReceived();
	}

	public Long getDamageDealt() {
		return damageDealt;
	}

	public void setDamageDealt(Long damageDealt) {
		this.damageDealt = damageDealt;
	}

	public Long getDamageAfterTrack() {
		return damageAfterTrack;
	}

	public void setDamageAfterTrack(Long damageAfterTrack) {
		this.damageAfterTrack = damageAfterTrack;
	}

	public Long getDamageAfterSpot() {
		return damageAfterSpot;
	}

	public void setDamageAfterSpot(Long damageAfterSpot) {
		this.damageAfterSpot = damageAfterSpot;
	}

	public Long getDamageReceived() {
		return damageReceived;
	}

	public void setDamageReceived(Long damageReceived) {
		this.damageReceived = damageReceived;
	}

}
