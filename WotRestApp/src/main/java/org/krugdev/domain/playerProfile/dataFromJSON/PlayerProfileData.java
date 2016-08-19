package org.krugdev.domain.playerProfile.dataFromJSON;

import com.google.gson.annotations.SerializedName;

public class PlayerProfileData {
	
	private Statistics statistics;
	@SerializedName("account_id")
	private long accountId;
	@SerializedName("created_at")
	private long createdAt;
	@SerializedName("updated_at")
	private long updatedAt;
	@SerializedName("global_rating")
	private long globalRating;
	@SerializedName("last_battle_time")
	private long lastBattleTime;
	private String nickname;
	
	public PlayerProfileData() {
		// TODO Auto-generated constructor stub
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public long getAccountId() {
		return accountId;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public long getGlobalRating() {
		return globalRating;
	}

	public long getLastBattleTime() {
		return lastBattleTime;
	}

	public String getNickname() {
		return nickname;
	}

	@Override
	public String toString() {
		return "PlayerProfileData [statistics=" + statistics + ", accountId=" + accountId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", globalRating=" + globalRating + ", lastBattleTime=" + lastBattleTime
				+ ", nickname=" + nickname + "]";
	}
	
}
