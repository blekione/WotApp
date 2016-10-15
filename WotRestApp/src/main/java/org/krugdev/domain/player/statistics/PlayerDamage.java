package org.krugdev.domain.player.statistics;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerStatisticsJSONBean;

@XmlRootElement
public class PlayerDamage extends PlayerStatistics {

	private Long damageDealt;
	private Long damageAfterTrack;
	private Long damageAfterSpot;
	private Long damageReceived;
	
	@Override
	public void populateWithDataFromJsonDataHolder(PlayerJSONBean playerJSONBean) {
		PlayerStatisticsJSONBean statistics = playerJSONBean.getStatistics();
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
