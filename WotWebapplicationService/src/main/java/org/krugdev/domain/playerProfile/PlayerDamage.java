package org.krugdev.domain.playerProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="playerDamage")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDamage {
	private Long damageDealt;
	private Long damageAfterTrack;
	private Long damageAfterSpot;
	private Long damageReceived;
	
	@Override
	public String toString() {
		return "PlayerDamage [damageDealt=" + damageDealt + ", damageAfterTrack=" + damageAfterTrack
				+ ", damageAfterSpot=" + damageAfterSpot + ", damageReceived=" + damageReceived + "]";
	}
	
	
}
