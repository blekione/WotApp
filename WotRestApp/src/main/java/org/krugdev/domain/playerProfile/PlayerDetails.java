package org.krugdev.domain.playerProfile;

import java.util.Date;

import org.krugdev.domain.Platforms;
import org.krugdev.domain.playerProfile.dataFromJSON.PlayerProfileData;

public class PlayerDetails { 
	
	private long accountId;
	private String nickname;
	private Platforms platform;
	private String clan;
	private long personalRating;
	private int daysInGame;

	public PlayerDetails() {
	}

	public static PlayerDetails populateWithData(PlayerProfileData playerData, Platforms platform) {
		PlayerDetails playerDetails = new PlayerDetails();
		playerDetails.accountId = playerData.getAccountId();
		playerDetails.nickname = playerData.getNickname();
		playerDetails.platform = platform;
//		TODO playerDetails.clan
		playerDetails.personalRating = playerData.getGlobalRating();
		playerDetails.daysInGame = calculateDaysInGame(playerData.getCreatedAt());
		return playerDetails;
	}

	private static int calculateDaysInGame(long createdAt) {
		Date date = new Date();
		return (int)((date.getTime() / 1000) - createdAt) / 86400;
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

	public String getClan() {
		return clan;
	}

	public long getPersonalRating() {
		return personalRating;
	}

}
