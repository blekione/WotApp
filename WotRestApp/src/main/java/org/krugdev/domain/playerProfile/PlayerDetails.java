package org.krugdev.domain.playerProfile;

public class PlayerDetails {
	
	private int accountId;
	private String name;
	private String platform;
	private String profileUrl;
	private String clan;
	private int personalRating;
	private int daysInGame;
	
	public PlayerDetails() {
	}
	
	public PlayerDetails(int accountId, String name, String platform, String profileUrl, 
			String clan, int personalRating,	int daysInGame) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.platform = platform;
		this.profileUrl = profileUrl;
		this.clan = clan;
		this.personalRating = personalRating;
		this.daysInGame = daysInGame;
	}
	
	

}
