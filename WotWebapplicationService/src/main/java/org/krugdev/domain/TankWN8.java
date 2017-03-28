package org.krugdev.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@XmlRootElement(name="wn8tanks")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class TankWN8 extends WN8{
	@XmlAttribute
	private int tankId;
	@XmlAttribute
	private double tankWN8;
	
	public String getWN8Color() {
		return super.getWN8Color(tankWN8);
	}
	
	public String getWN8Rank() {
		return super.getWN8Rank(tankWN8);
	}

}
