package org.krugdev.domain.playerProfile;

import java.util.Date;

import org.krugdev.domain.Platforms;
import org.krugdev.domain.playerProfile.JsonDataBeans.Clan;
import org.krugdev.domain.playerProfile.JsonDataBeans.Player;
import org.krugdev.domain.playerProfile.JsonDataBeans.PlayerClan;

public class PlayerDetails { 
	
	private long accountId;
	private String nickname;
	private Platforms platform;
	private String clan;
	private String clanTag;
	private int clanID;
	private String clanRole;
	private int daysInClan;
	private long personalRating;
	private int daysInGame;

	public PlayerDetails() {
	}

	public void populateWithDataFromJsonDataHolders(WotData data) {
		Player player = data.getPlayer();
		PlayerClan playerClan = data.getPlayerClan();
		Clan clan = data.getClan();
		this.accountId = player.getAccountId();
		this.nickname = player.getNickname();
		this.clan = clan.getName();
		this.clanTag = clan.getTag();
		this.clanID = playerClan.getClanId();
		this.clanRole = playerClan.getClanRole();
		this.daysInClan = changeTimeToDays(playerClan.getJoinedAt());
		this.personalRating = player.getGlobalRating();
		this.daysInGame = changeTimeToDays(player.getCreatedAt());
	}

	private static int changeTimeToDays(long timeInMs) {
		Date date = new Date();
		return (int)((date.getTime() / 1000) - timeInMs) / 86400;
	}	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getDaysInGame() {
		return daysInGame;
	}

	public long getAccountId() {
		return accountId;
	}

	public Platforms getPlatform() {
		return platform;
	}
	
	public void setPlatform(Platforms platform) {
		this.platform = platform;
	}

	public String getClan() {
		return clan;
	}
	
	public void setClan(String clan) {
		this.clan = clan;
	}

	public String getClanTag() {
		return clanTag;
	}
	
	
	public int getClanID() {
		return clanID;
	}

	public void setClanID(int clanID) {
		this.clanID = clanID;
	}

	public long getPersonalRating() {
		return personalRating;
	}

	public String getClanRole() {
		return clanRole;
	}

	public void setClanRole(String clanRole) {
		this.clanRole = clanRole;
	}

	public int getDaysInClan() {
		return daysInClan;
	}

	public void setDaysInClan(int daysInClan) {
		this.daysInClan = daysInClan;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setPersonalRating(long personalRating) {
		this.personalRating = personalRating;
	}

	public void setDaysInGame(int daysInGame) {
		this.daysInGame = daysInGame;
	}
	
	
}
