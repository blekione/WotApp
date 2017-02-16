package org.krugdev.wn8;

public class TankItemBuilder {

	private final PlayerTanks player;
	private final int tankId;
	private int gamesCount;
	private int frags;
	private long damageDealt;
	private int spottedTanks;
	private int defencePoints;
	private double winRatio;

	public TankItemBuilder(PlayerTanks player, int tankId) {
		this.player = player;
		this.tankId = tankId;
	}

	public TankItemBuilder gamesCount(int gamesCount) {
		this.gamesCount = gamesCount;
		return this;
	}

	public TankItemBuilder frags(int frags) {
		this.frags = frags;
		return this;
	}

	public TankItemBuilder damageDealt(long damageDealt) {
		this.damageDealt = damageDealt;
		return this;
	}

	public TankItemBuilder spottedTanks(int spottedTanks) {
		this.spottedTanks = spottedTanks;
		return this;
	}

	public TankItemBuilder defencePoints(int defencePoints) {
		this.defencePoints = defencePoints;
		return this;
	}

	public TankItemBuilder winRatio(double winRatio) {
		this.winRatio = winRatio;
		return this;
	}

	public TankItem build() {
		return new TankItem(player, tankId, gamesCount, frags, damageDealt, spottedTanks, defencePoints, winRatio);
	}
	
	
}
