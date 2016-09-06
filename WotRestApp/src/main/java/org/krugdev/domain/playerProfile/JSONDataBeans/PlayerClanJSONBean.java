package org.krugdev.domain.playerProfile.JSONDataBeans;

import com.google.gson.annotations.SerializedName;

public class PlayerClanJSONBean {
	@SerializedName("in_clan_cooldown_till")
	private long inClanCooldownTill;
	@SerializedName("joined_at")
	private long joinedAt;
	@SerializedName("clan_id")
	private int clanId;
	@SerializedName("role")
	private String clanRole;
	
	public PlayerClanJSONBean() {
	}

	public long getInClanCooldownTill() {
		return inClanCooldownTill;
	}

	public void setInClanCooldownTill(long inClanCooldownTill) {
		this.inClanCooldownTill = inClanCooldownTill;
	}

	public long getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(long joinedAt) {
		this.joinedAt = joinedAt;
	}

	public int getClanId() {
		return clanId;
	}

	public void setClanId(int clanId) {
		this.clanId = clanId;
	}

	public String getClanRole() {
		return clanRole;
	}

	public void setClanRole(String clanRole) {
		this.clanRole = clanRole;
	}

	@Override
	public String toString() {
		return "PlayerClan [inClanCooldownTill=" + inClanCooldownTill + ", joinedAt=" + joinedAt + ", clanId=" + clanId
				+ ", clanRole=" + clanRole + "]";
	}
}
