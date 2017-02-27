package org.krugdev.wn8.XML;

import org.krugdev.wn8.PlayerTanks;

public class XMLTankItemBuilder {

	private final PlayerTanks player;
	private final int tankId;
	private int gamesCount;
	private int frags;
	private long damageDealt;
	private int spottedTanks;
	private int defencePoints;
	private double winRatio;

	public XMLTankItemBuilder(PlayerTanks player, int tankId) {
		this.player = player;
		this.tankId = tankId;
	}

	public XMLTankItemBuilder gamesCount(int gamesCount) {
		this.gamesCount = gamesCount;
		return this;
	}

	public XMLTankItemBuilder frags(int frags) {
		this.frags = frags;
		return this;
	}

	public XMLTankItemBuilder damageDealt(long damageDealt) {
		this.damageDealt = damageDealt;
		return this;
	}

	public XMLTankItemBuilder spottedTanks(int spottedTanks) {
		this.spottedTanks = spottedTanks;
		return this;
	}

	public XMLTankItemBuilder defencePoints(int defencePoints) {
		this.defencePoints = defencePoints;
		return this;
	}

	public XMLTankItemBuilder winRatio(double winRatio) {
		this.winRatio = winRatio;
		return this;
	}

	public TankItem build() {
		return new TankItem(player, tankId, gamesCount, frags, damageDealt, spottedTanks, defencePoints, winRatio);
	}
	
	
}
