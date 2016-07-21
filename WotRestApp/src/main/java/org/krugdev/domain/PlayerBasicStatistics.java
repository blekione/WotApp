package org.krugdev.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerBasicStatistics {
	
	@SerializedName("account_id")
	private int accountId;
	private String name;
	private double wins;
	private String platform;
	@SerializedName("battles_count")
	private int battlesCount;
	@SerializedName("profile_url")
	private String profileUrl;
	
	public PlayerBasicStatistics() {
	}

	public PlayerBasicStatistics(int accountId, double wins, String platform, int battlesCount, String profileUrl, String name) {
		this.accountId = accountId;
		this.wins = wins;
		this.platform = platform;
		this.battlesCount = battlesCount;
		this.profileUrl = profileUrl;
		this.name = name;
	}
	
	public int getAccountId() {	return accountId; }
	public double getWins() { return wins; }
	public String getPlatform() { return platform; }
	public int getBattlesCount() { return battlesCount;	}
	public String getProfileUrl() { return profileUrl; }
	public String getName() { return name; }
	
	@Override
	public String toString() {
		return "PlayerBasic [accountId=" + accountId + ", wins=" + wins + ", platform=" + platform + ", battlesCount="
				+ battlesCount + ", profileUrl=" + profileUrl + ", name=" + name + "]";
	}
	
}
