package org.krugdev.wn8.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.krugdev.wn8.PlayerTanks;

import lombok.Getter;

@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TankItem {

	@XmlTransient
	private PlayerTanks player;
	private int tankId;
	private int gamesCount;
	private int frags;
	private long damageDealt;
	private int spottedTanks;
	private int defencePoints;
	private double winRatio;
	
	public TankItem() {
	}

	protected TankItem(PlayerTanks player, int tankId, int gamesCount, int frags, long damageDealt, int spottedTanks,
			int defencePoints, double winRatio) {
				this.player = player;
				this.tankId = tankId;
				this.gamesCount = gamesCount;
				this.frags = frags;
				this.damageDealt = damageDealt;
				this.spottedTanks = spottedTanks;
				this.defencePoints = defencePoints;
				this.winRatio = winRatio;
	}

	@Override
	public String toString() {
		return "TankItem [player=" + player + ", tankId=" + tankId + ", gamesCount=" + gamesCount + ", frags=" + frags
				+ ", damageDealt=" + damageDealt + ", spottedTanks=" + spottedTanks + ", defencePoints=" + defencePoints
				+ ", winRatio=" + winRatio + "]";
	}
}
