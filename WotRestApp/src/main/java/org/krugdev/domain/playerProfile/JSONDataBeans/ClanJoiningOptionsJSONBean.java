package org.krugdev.domain.playerProfile.JSONDataBeans;

import com.google.gson.annotations.SerializedName;

public class ClanJoiningOptionsJSONBean {

	@SerializedName("wins_ratio")
	private double winsRatio;
	@SerializedName("battles_per_day")
	private int battlesPerDay;
	@SerializedName("damage_per_battle")
	private int damagePerBattle;
	@SerializedName("battles_survived")
	private double battlesSurvived;
	@SerializedName("hitsRatio")
	private double hitsRatio;
	private int battles;
	@SerializedName("xp_per_battle")
	private int xpPerBattle;
	
	public ClanJoiningOptionsJSONBean() {
	}
	
}
