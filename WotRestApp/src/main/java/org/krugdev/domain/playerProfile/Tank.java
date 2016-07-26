package org.krugdev.domain.playerProfile;

public class Tank {
	
	private int tankRef;
	private String tankName;
	private double expectedFrag;
	private double expectedDamage;
	private double expectedSpot;
	private double expectedDefensePoints;
	private double expectedWinRate;
	private int tier;
	private String nation;
	
	public Tank() {

	}

	public Tank(int tankRef, String tankName, double expectedFrag, double expectedDamage, double expectedSpot,
			double expectedDefensePoints, double expectedWinRate, int tier, String nation) {
		super();
		this.tankRef = tankRef;
		this.tankName = tankName;
		this.expectedFrag = expectedFrag;
		this.expectedDamage = expectedDamage;
		this.expectedSpot = expectedSpot;
		this.expectedDefensePoints = expectedDefensePoints;
		this.expectedWinRate = expectedWinRate;
		this.tier = tier;
		this.nation = nation;
	}

	public int getTankRef() {
		return tankRef;
	}

	public void setTankRef(int tankRef) {
		this.tankRef = tankRef;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public double getExpectedFrag() {
		return expectedFrag;
	}

	public void setExpectedFrag(double expectedFrag) {
		this.expectedFrag = expectedFrag;
	}

	public double getExpectedDamage() {
		return expectedDamage;
	}

	public void setExpectedDamage(double expectedDamage) {
		this.expectedDamage = expectedDamage;
	}

	public double getExpectedSpot() {
		return expectedSpot;
	}

	public void setExpectedSpot(double expectedSpot) {
		this.expectedSpot = expectedSpot;
	}

	public double getExpectedDefensePoints() {
		return expectedDefensePoints;
	}

	public void setExpectedDefensePoints(double expectedDefensePoints) {
		this.expectedDefensePoints = expectedDefensePoints;
	}

	public double getExpectedWinRate() {
		return expectedWinRate;
	}

	public void setExpectedWinRate(double expectedWinRate) {
		this.expectedWinRate = expectedWinRate;
	}

	public int getTier() {
		return tier;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	
}
