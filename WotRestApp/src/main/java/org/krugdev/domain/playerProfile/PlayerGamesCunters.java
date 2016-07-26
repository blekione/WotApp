package org.krugdev.domain.playerProfile;

public class PlayerGamesCunters {

	private int battlesCount;
	private int battlesWins;
	private int battlesLosses;
	private int battlesDraws;
	private int battlesSurvived;
	
	public PlayerGamesCunters() {
	}

	public PlayerGamesCunters(int battlesCount, int battlesWins, int battlesLosses, int battlesDraws,
			int battlesSurvived) {
		super();
		this.battlesCount = battlesCount;
		this.battlesWins = battlesWins;
		this.battlesLosses = battlesLosses;
		this.battlesDraws = battlesDraws;
		this.battlesSurvived = battlesSurvived;
	}
	
	
}
