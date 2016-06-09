package org.krugdev.domain;

import com.google.gson.annotations.SerializedName;

public class PlayerBasic {
	
	@SerializedName("account_id")
	private int accountId;
	private double wins;
	private String platform;
	@SerializedName("battles_count")
	private int battlesCount;
	@SerializedName("profileUrl")
	private String profile_url;
	private String name;
	
	public PlayerBasic(int accountId, double wins, String platform, int battlesCount, String profile_url, String name) {
		this.accountId = accountId;
		this.wins = wins;
		this.platform = platform;
		this.battlesCount = battlesCount;
		this.profile_url = profile_url;
		this.name = name;
	}
	
	public int getAccountId() {	return accountId; }
	public double getWins() { return wins; }
	public String getPlatform() { return platform; }
	public int getBattlesCount() { return battlesCount;	}
	public String getProfile_url() { return profile_url; }
	public String getName() { return name; }
	
	@Override
	public String toString() {
		return "PlayerBasic [accountId=" + accountId + ", wins=" + wins + ", platform=" + platform + ", battlesCount="
				+ battlesCount + ", profile_url=" + profile_url + ", name=" + name + "]";
	}
	
}
