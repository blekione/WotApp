package org.krugdev.domain.playerTanks.JSONDataBeans;

import com.google.gson.annotations.SerializedName;

public class TankJSONBean {

	private TankGameModeStatistics all;
	@SerializedName("last_battle_time")
	private int lastBattleTime;
	@SerializedName("account_id")
	private long accountId;
	@SerializedName("max_xp")
	private int maxXp;
	private TankGameModeStatistics company;
	@SerializedName("trees_cut")
	private long treesCut;
	@SerializedName("in_garage_updated")
	private int inGarageUpdated;
	@SerializedName("max_frags")
	private int maxFrags;
	private int frags;
	@SerializedName("mark_of_mastery")
	private int markOfMastery;
	@SerializedName("battle_life_time")
	private int battleLifeTime;
	@SerializedName("in_garage")
	private boolean inGarage;
	@SerializedName("tank_id")
	private int tankId;
	public TankGameModeStatistics getAll() {
		return all;
	}
	public int getLastBattleTime() {
		return lastBattleTime;
	}
	public long getAccountId() {
		return accountId;
	}
	public int getMaxXp() {
		return maxXp;
	}
	public TankGameModeStatistics getCompany() {
		return company;
	}
	public long getTreesCut() {
		return treesCut;
	}
	public int getInGarageUpdated() {
		return inGarageUpdated;
	}
	public int getMaxFrags() {
		return maxFrags;
	}
	public int getFrags() {
		return frags;
	}
	public int getMarkOfMastery() {
		return markOfMastery;
	}
	public int getBattleLifeTime() {
		return battleLifeTime;
	}
	public boolean isInGarage() {
		return inGarage;
	}
	public int getTankId() {
		return tankId;
	}
	
}
