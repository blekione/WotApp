package org.krugdev.domain.playerProfile.statistics;

import java.util.Date;

import org.krugdev.domain.playerProfile.WotData;
import org.krugdev.domain.playerProfile.JSONDataBeans.ClanJSONBean;
import org.krugdev.domain.playerProfile.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.playerProfile.JSONDataBeans.PlayerClanJSONBean;

public class Player implements PlayerStatistics{ 
	
	private String nickname;
	private String clan;
	private String clanTag;
	private int clanID;
	private String clanRole;
	private int daysInClan;
	private long personalRating;
	private int daysInGame;
	private long vehiclesSpotted;
	private long baseCapturePoints;
	private long baseDefensePoints;

	public Player() {
	}

	public void populateWithDataFromJsonDataHolders(WotData data) {
		PlayerJSONBean player = data.getPlayer();
		PlayerClanJSONBean playerClan = data.getPlayerClan();
		ClanJSONBean clan = data.getClan();
		this.nickname = player.getNickname();
		this.clan = clan.getName();
		this.clanTag = clan.getTag();
		this.clanID = playerClan.getClanId();
		this.clanRole = playerClan.getClanRole();
		this.daysInClan = changeTimeToDays(playerClan.getJoinedAt());
		this.personalRating = player.getGlobalRating();
		this.daysInGame = changeTimeToDays(player.getCreatedAt());
		this.vehiclesSpotted = player.getStatistics().getAll().getSpotted();
		this.baseDefensePoints = player.getStatistics().getAll().getDroppedCapturePoints();
		this.baseCapturePoints = player.getStatistics().getAll().getCapturePoints();
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

	public void setPersonalRating(long personalRating) {
		this.personalRating = personalRating;
	}

	public void setDaysInGame(int daysInGame) {
		this.daysInGame = daysInGame;
	}

	public long getVehiclesSpotted() {
		return vehiclesSpotted;
	}

	public long getBaseCapturePoints() {
		return baseCapturePoints;
	}

	public long getBaseDefensePoints() {
		return baseDefensePoints;
	}
	
}
