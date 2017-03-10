package org.krugdev.rservice.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="wn8")
public class PlayerWN8 {
	
	@XmlAttribute
	private double wn8;
	@XmlAttribute
	private int playerId;
	
	public PlayerWN8() {
	}

	public PlayerWN8(double d, int playerId) {
		this.wn8 = d;
		this.playerId = playerId;
	}
}
