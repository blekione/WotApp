package org.krugdev.wn8.expected;

import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;

@Getter
public class TankExpectedValues {

	@XmlAttribute
	private double expFrag;
	@XmlAttribute
	private double expDamage;
	@XmlAttribute
	private double expSpot;
	@XmlAttribute
	private double expDef;
	@XmlAttribute
	private double expWinRate;
	@XmlAttribute(name="IDNum")
	private int IDNum;
	@XmlAttribute(name="countryid")
	private int countryId;
	@XmlAttribute(name="tankid")
	private int tankId;

	public TankExpectedValues() {
	}
	
	public TankExpectedValues(double expFrag, double expDamage, double expSpot, double expDef, double expWinRate) {
		this.expFrag = expFrag;
		this.expDamage = expDamage;
		this.expSpot = expSpot;
		this.expDef = expDef;
		this.expWinRate = expWinRate;
	}
}
