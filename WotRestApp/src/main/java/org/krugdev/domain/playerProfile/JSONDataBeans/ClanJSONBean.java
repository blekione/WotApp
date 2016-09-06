package org.krugdev.domain.playerProfile.JSONDataBeans;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ClanJSONBean {

	@SerializedName("creator_id")
	private int creatorId;
	@SerializedName("members_count")
	private int membersCount;
	private String description;
	@SerializedName("creator_name")
	private String creatorName;
	private String color;
	@SerializedName("clan_id")
	private int clanId;
	@SerializedName("created_at")
	private long createdAt;
	@SerializedName("updated_at")
	private long updatedAt;
	@SerializedName("leader_name")
	private String leaderName;
	@SerializedName("members_ids")
	private List<Integer> membersIds;
	@SerializedName("recruiting_policy")
	private String recruitingPolicy;
	private String tag;
	@SerializedName("emblem_set_id")
	private int emblemSetId;
	@SerializedName("is_clan_disbanded")
	private boolean isClanDisbanded;
	@SerializedName("old_name")
	private String oldName;
	@SerializedName("joining_options")
	private ClanJoiningOptionsJSONBean joiningOptions;
	@SerializedName("leader_id")
	private int leaderId;
	private String motto;
	@SerializedName("renamed_at")
	private long renamedAt;
	@SerializedName("old_tag")
	private String oldTag;
	private String name;
	
	public ClanJSONBean() {
	}

	public List<Integer> getMembersIds() {
		return membersIds;
	}

	public void setMembersIds(List<Integer> menbersIds) {
		this.membersIds = menbersIds;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public int getMembersCount() {
		return membersCount;
	}

	public String getDescription() {
		return description;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public String getColor() {
		return color;
	}

	public int getClanId() {
		return clanId;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public String getRecruitingPolicy() {
		return recruitingPolicy;
	}

	public String getTag() {
		return tag;
	}

	public int getEmblemSetId() {
		return emblemSetId;
	}

	public boolean isClanDisbanded() {
		return isClanDisbanded;
	}

	public String getOldName() {
		return oldName;
	}

	public ClanJoiningOptionsJSONBean getJoiningOptions() {
		return joiningOptions;
	}

	public int getLeaderId() {
		return leaderId;
	}

	public String getMotto() {
		return motto;
	}

	public long getRenamedAt() {
		return renamedAt;
	}

	public String getOldTag() {
		return oldTag;
	}

	public String getName() {
		return name;
	}

}
