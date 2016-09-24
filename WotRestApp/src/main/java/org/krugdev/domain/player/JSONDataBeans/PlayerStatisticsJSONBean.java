package org.krugdev.domain.player.JSONDataBeans;

import com.google.gson.annotations.SerializedName;

public class PlayerStatisticsJSONBean {

	@SerializedName("max_frags_tank_id")
	private int maxFragsTankId;
	@SerializedName("explosion_hits")
	private long explosionHits;
	@SerializedName("max_xp_tank_id")
	private int maxXpTankId;
	@SerializedName("damage_assisted_track")
	private long damageAssistedTrack;
	@SerializedName("max_xp")
	private int maxXp;
	private long piercings;
	@SerializedName("trees_cut")
	private long treesCut;
	private PlayerGameModeStatisticsJSONBean company;
	private PlayerGameModeStatisticsJSONBean all;
	@SerializedName("piercings_received")
	private long piercingsReceived;
	@SerializedName("no_damage_direct_hits_received")
	private long noDamageDirectHitsReceived;
	@SerializedName("max_frags")
	private int maxFrags;
	@SerializedName("explosion_hits_received")
	private long explosionHitsReceived;
	@SerializedName("max_damage_tank_id")
	private int maxDamageTankId;
	private int frags;
	@SerializedName("direct_hits_received")
	private long directHitsReceived;
	@SerializedName("max_damage")
	private int maxDamage;
	@SerializedName("damage_assisted_radio")
	private long damageAssistedRadio;
	
	public int getMaxFragsTankId() {
		return maxFragsTankId;
	}

	public long getExplosionHits() {
		return explosionHits;
	}

	public int getMaxXpTankId() {
		return maxXpTankId;
	}

	public long getDamageAssistedTrack() {
		return damageAssistedTrack;
	}

	public int getMaxXp() {
		return maxXp;
	}

	public long getPiercings() {
		return piercings;
	}

	public PlayerGameModeStatisticsJSONBean getCompany() {
		return company;
	}

	public long getTreesCut() {
		return treesCut;
	}

	public PlayerGameModeStatisticsJSONBean getAll() {
		return all;
	}

	public long getPiercingsReceived() {
		return piercingsReceived;
	}

	public long getNoDamageDirectHitsReceived() {
		return noDamageDirectHitsReceived;
	}

	public int getMaxFrags() {
		return maxFrags;
	}

	public long getExplosionHitsReceived() {
		return explosionHitsReceived;
	}

	public int getMaxDamageTankId() {
		return maxDamageTankId;
	}

	public int getFrags() {
		return frags;
	}

	public long getDirectHitsReceived() {
		return directHitsReceived;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public long getDamageAssistedRadio() {
		return damageAssistedRadio;
	}

	@Override
	public String toString() {
		return "Statistics [maxFragsTankId=" + maxFragsTankId + ", explosionHits=" + explosionHits + ", maxXpTankId="
				+ maxXpTankId + ", damageAssistedTrack=" + damageAssistedTrack + ", maxXp=" + maxXp + ", piercings="
				+ piercings + ", company=" + company + ", treesCut=" + treesCut + ", all=" + all
				+ ", piercingsReceived=" + piercingsReceived + ", noDamageDirectHitsReceived="
				+ noDamageDirectHitsReceived + ", maxFrags=" + maxFrags + ", explosionHitsReceived="
				+ explosionHitsReceived + ", maxDamageTankId=" + maxDamageTankId + ", frags=" + frags
				+ ", directHitsReceived=" + directHitsReceived + ", maxDamage=" + maxDamage + ", damageAssistedRadio="
				+ damageAssistedRadio + "]";
	}
	
}
