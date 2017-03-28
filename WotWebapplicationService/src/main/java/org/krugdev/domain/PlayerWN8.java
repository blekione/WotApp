package org.krugdev.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@XmlRootElement(name="wn8")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class PlayerWN8 extends WN8 {

	@XmlAttribute
	private double wn8 = 0;
	@XmlAttribute
	private int playerId;
	
	public String getWN8Color() {
		return super.getWN8Color(wn8);
	}
	
	public String getWN8Rank() {
		return super.getWN8Rank(wn8);
	}
}
