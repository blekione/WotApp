package org.krugdev.domain.playerProfile.dataFromJSON;

import com.google.gson.annotations.SerializedName;

public class StatisticsFor {
	
	private long spotted;
	private long hits;
	private int wins;
	private int losses;
	@SerializedName("capture_points")
	private long capturePoints;
	private int battles;
	@SerializedName("damage_dealt")
	private long damageDealt;
	@SerializedName("damage_received")
	private long damageReceived;
	private long shots;
	private long xp;
	private long frags;
	@SerializedName("survived_battles")
	private int survivedBattles;
	@SerializedName("dropped_capture_points")
	private long droppedCapturePoints;
	
	public StatisticsFor() {
		// TODO Auto-generated constructor stub
	}

	public long getSpotted() {
		return spotted;
	}

	public long getHits() {
		return hits;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public long getCapturePoints() {
		return capturePoints;
	}

	public int getBattles() {
		return battles;
	}

	public long getDamageDealt() {
		return damageDealt;
	}

	public long getDamageReceived() {
		return damageReceived;
	}

	public long getShots() {
		return shots;
	}

	public long getXp() {
		return xp;
	}

	public long getFrags() {
		return frags;
	}

	public int getSurvivedBattles() {
		return survivedBattles;
	}

	public long getDroppedCapturePoints() {
		return droppedCapturePoints;
	}

	@Override
	public String toString() {
		return "StatisticsFor [spotted=" + spotted + ", hits=" + hits + ", wins=" + wins + ", losses=" + losses
				+ ", capturePoints=" + capturePoints + ", battles=" + battles + ", damageDealt=" + damageDealt
				+ ", damageReceived=" + damageReceived + ", shots=" + shots + ", xp=" + xp + ", frags=" + frags
				+ ", survivedBattles=" + survivedBattles + ", droppedCapturePoints=" + droppedCapturePoints + "]";
	}
	
}
