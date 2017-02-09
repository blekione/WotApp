package org.krugdev.wn8;

import lombok.Getter;

public class TankExpectedValues {

	@Getter
	private double expFrag;
	@Getter
	private double expDamage;
	@Getter
	private double expSpot;
	@Getter
	private double expDef;
	@Getter
	private double expWinRate;

	public TankExpectedValues(double expFrag, double expDamage, double expSpot, double expDef, double expWinRate) {
		this.expFrag = expFrag;
		this.expDamage = expDamage;
		this.expSpot = expSpot;
		this.expDef = expDef;
		this.expWinRate = expWinRate;
	}
}
