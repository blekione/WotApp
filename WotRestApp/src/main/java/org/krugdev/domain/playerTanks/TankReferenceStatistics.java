package org.krugdev.domain.playerTanks;

public class TankReferenceStatistics {
	
	private String tankName;
	private int tankRef;
	
	public TankReferenceStatistics(String tankName, int tankRef) {
		super();
		this.tankName = tankName;
		this.tankRef = tankRef;
	}

	public String getTankName() { return tankName; }
	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public int getTankRef() { return tankRef; }
	public void setTankRef(int tankRef) {
		this.tankRef = tankRef;
	}
}
