package org.krugdev.domain.playerTanks.JSONDataBeans;

import com.google.gson.annotations.SerializedName;

public class TankGameModeStatistics {

	private int spotted;
	@SerializedName("piercings_received")
	private int piercingsReceived;
	private int hits;
	@SerializedName("damage_assisted_track")
	private long damageAssistedTrack;
	private int wins;
	private int losses;
	@SerializedName("no_damage_direct_hits_received")
	private int noDamageDirectHitsReceived;
	@SerializedName("capture_points")
	private int capturePoints;
	private int battles;
	@SerializedName("damage_dealt")
	private long damageDealt;
	@SerializedName("explosion_hits")
	private int explosionHits;
	@SerializedName("damage_received")
	private long damageReceived;
	private int piercings;
	private int shots;
	@SerializedName("explosion_hits_received")
	private int explosionHitsReceived;
	@SerializedName("damage_assisted_radio")
	private long damageAssistedRadio;
	private long xp;
	@SerializedName("direct_hits_received")
	private int directHitsReceived;
	private int frags;
	@SerializedName("survived_battles")
	private int survivedBattles;
	@SerializedName("dropped_capture_points")
	private int droppedCapturePoints;
	public int getSpotted() {
		return spotted;
	}
	public int getPiercingsReceived() {
		return piercingsReceived;
	}
	public int getHits() {
		return hits;
	}
	public long getDamageAssistedTrack() {
		return damageAssistedTrack;
	}
	public int getWins() {
		return wins;
	}
	public int getLosses() {
		return losses;
	}
	public int getNoDamageDirectHitsReceived() {
		return noDamageDirectHitsReceived;
	}
	public int getCapturePoints() {
		return capturePoints;
	}
	public int getBattles() {
		return battles;
	}
	public long getDamageDealt() {
		return damageDealt;
	}
	public int getExplosionHits() {
		return explosionHits;
	}
	public long getDamageReceived() {
		return damageReceived;
	}
	public int getPiercings() {
		return piercings;
	}
	public int getShots() {
		return shots;
	}
	public int getExplosionHitsReceived() {
		return explosionHitsReceived;
	}
	public long getDamageAssistedRadio() {
		return damageAssistedRadio;
	}
	public long getXp() {
		return xp;
	}
	public int getDirectHitsReceived() {
		return directHitsReceived;
	}
	public int getFrags() {
		return frags;
	}
	public int getSurvivedBattles() {
		return survivedBattles;
	}
	public int getDroppedCapturePoints() {
		return droppedCapturePoints;
	}
	
}

