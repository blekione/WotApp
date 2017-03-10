package org.krugdev.wn8.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.XML.XMLTankItemBuilder;

import lombok.Getter;

@Getter
@Entity
public class DBTankItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private PlayerTimestamp player;
	private int tankId;
	private int gamesCount;
	private int frags;
	private long damageDealt;
	private int spottedTanks;
	private int defencePoints;
	private double winRatio;
	private double tankWN8;

	// default constructor required by persistence
	public DBTankItem() {
	}

	public static DBTankItem instanceOf(TankItem tankItem, double wn8) {
		DBTankItem persistenceTankItem = new DBTankItem();	
		persistenceTankItem.tankId = tankItem.getTankId();
		persistenceTankItem.gamesCount = tankItem.getGamesCount();
		persistenceTankItem.frags = tankItem.getFrags();
		persistenceTankItem.damageDealt = tankItem.getDamageDealt();
		persistenceTankItem.spottedTanks = tankItem.getSpottedTanks();
		persistenceTankItem.defencePoints = tankItem.getDefencePoints();
		persistenceTankItem.winRatio = tankItem.getWinRatio();
		persistenceTankItem.tankWN8 = wn8;
		return persistenceTankItem;
	}

	public static DBTankItem instanceOf(TankItem tankItem) {
		return DBTankItem.instanceOf(tankItem, 1000);
	}
	
	public TankItem convertToTankItem() {
		return new XMLTankItemBuilder(this.player, this.tankId)
				.gamesCount(this.gamesCount)
				.frags(this.frags)
				.damageDealt(this.damageDealt)
				.spottedTanks(this.spottedTanks)
				.defencePoints(this.defencePoints)
				.winRatio(this.winRatio)
				.build();
	}
	
	public void setPlayer(PlayerTimestamp player) {
		this.player = player;
	}
	@Override
	public String toString() {
		return "TankItem [player=" + player + ", tankId=" + tankId + ", gamesCount=" + gamesCount + ", frags=" + frags
				+ ", damageDealt=" + damageDealt + ", spottedTanks=" + spottedTanks + ", defencePoints=" + defencePoints
				+ ", winRatio=" + winRatio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (damageDealt ^ (damageDealt >>> 32));
		result = prime * result + defencePoints;
		result = prime * result + frags;
		result = prime * result + gamesCount;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + spottedTanks;
		result = prime * result + tankId;
		long temp;
		temp = Double.doubleToLongBits(winRatio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBTankItem other = (DBTankItem) obj;
		if(other.player instanceof PlayerTimestamp) {
			if (!player.equals(other.getPlayer())) {
				return false;
			}
		}
		if (damageDealt != other.damageDealt)
			return false;
		if (defencePoints != other.defencePoints)
			return false;
		if (frags != other.frags)
			return false;
		if (gamesCount != other.gamesCount)
			return false;
		if (id != other.id)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (spottedTanks != other.spottedTanks)
			return false;
		if (tankId != other.tankId)
			return false;
		if (Double.doubleToLongBits(winRatio) != Double.doubleToLongBits(other.winRatio))
			return false;
		return true;
	}
}
